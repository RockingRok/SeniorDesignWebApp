package webapp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

public class VideoServlet extends HttpServlet {
	private final String connectionType = "http://";
	
	static {
		ObjectifyService.register(Video.class);
	}

	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		List<BlobKey> blobKeys = blobs.get("myFile");
		if (blobKeys == null || blobKeys.size() == 0) {
			res.sendRedirect(req.getHeader("referer"));
		} else {
			List<Video> videos = ObjectifyService.ofy().load().type(Video.class).list();
			try {
				if (videos.size() > 0) {
					blobstoreService.delete(new BlobKey(videos.get(0).getBlobKey()));
					ofy().delete().entity(videos.get(0)).now();
				}
				JSONObject response = sendVideoLinkToServer("http://" + Utils.getHostName(8080) + "/serve?blob-key=" + blobKeys.get(0).getKeyString(), blobKeys.get(0).getKeyString());
				ofy().save().entity(new Video(user, blobKeys.get(0).getKeyString(), response)).now();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			res.sendRedirect(req.getHeader("referer"));
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
		blobstoreService.serve(blobKey, resp);
	}

	public JSONObject sendVideoLinkToServer(String videoURL, String key) throws Exception {
		// make POST request
		String jsonContent = "{\"video\": \"" + videoURL + "\",\"key\": \"" + key + "\"}";
		System.out.println(jsonContent);
		// String data = "{\"document\" : {\"_id\": \"" + id+ "\", \"context\":" +
		// context +"}}";
		URL url = new URL("http://" + Utils.getHostName(5000));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setInstanceFollowRedirects(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("content-type", "application/json");
		connection.setRequestProperty("charset", "utf-8");
		connection.setRequestProperty("Content-Length", "" + Integer.toString(jsonContent.getBytes().length));
		connection.setUseCaches(false);

		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		writer.write(jsonContent);
		writer.close();
		System.out.println(connection.getResponseMessage());

		InputStream response;

		// Check for error , if none store response
		if (connection.getResponseCode() == 200) {
			response = connection.getInputStream();
		} else {
			response = connection.getErrorStream();
		}
		InputStreamReader isr = new InputStreamReader(response);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(isr);
		String read = br.readLine();
		while (read != null) {
			sb.append(read);
			read = br.readLine();
		}
		// Print the String
		System.out.println(sb.toString());
		
		JSONObject result = new JSONObject(sb.toString());
		
		System.out.println(result);

		connection.disconnect();
		return result;
	}
}