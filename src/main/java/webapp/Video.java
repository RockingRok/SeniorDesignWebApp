package webapp;
import java.util.Date;

import org.json.JSONObject;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Video implements Comparable<Video> {
    @Parent Key<UserInstance> user;
    @Id Long id;
    @Index String blobKey;
    @Index Date date;
    
    private String videoOutput;
    
    private Video() {}
    
    public Video(User user, String blobKey, JSONObject serverResponse) throws Exception { 
    	this.user = Key.create(UserInstance.class, "default");
    	this.blobKey = blobKey;
    	this.videoOutput = serverResponse.getJSONObject("predictions").getString("output_video");
        this.date = new Date();
    }
    
    public String getBlobKey() {
        return blobKey;
    }
    
    public String getVideoResults() {
    	return videoOutput;
    }

    @Override
    public int compareTo(Video other) {
        if (date.after(other.date)) {
            return 1;
        } else if (date.before(other.date)) {
            return -1;
        }
        return 0;
     }
}