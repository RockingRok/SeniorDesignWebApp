<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="com.googlecode.objectify.*"%>
<%@ page import="webapp.Video"%>
<%@ page import="webapp.Utils"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	//BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>

<!doctype HTML>
<html>

	<head>
		<meta charset="utf-8" />
		<title>EE464 Video Recognition</title>
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
	</head>
	
	
	<body>

		<!-- Header -->
			<header id="header">
				<div class="logo"><a href="videoUpload.jsp">EE464 <span>ORSHANSKY</span></a></div>
				<a id="menu_refer" href="#menu"><span>Menu</span></a>
			</header>

		<!-- Nav -->
			<nav id="menu">
				<ul class="links" id="clickmenu">
					<li><a id="home" href="videoUpload.jsp">Home</a></li>
					<li><a id="meetteam" href="teampage.html">Meet the Team</a></li>
					<li><a id="research" href="research.html">Research</a></li>
				</ul>
			</nav>


		<!-- Banner -->
			<section id="banner" class="bg-img" data-bg="movingBanner.gif">
				<div class="inner">
					<header>
						<h1>Complex Action Recognition</h1>
					</header>
				</div>
				<a href="#try" class="more">Try It Out</a>
			</section>

		<!-- Try Section -->
			<section id="try" class="wrapper post bg-img">
				<div class="inner">
					<article class="box">
						<header>
							<h2>Video Examples</h2>
							<br>
							<p>Below are videos with atomic action and object information transposed on. 
							This is the result of random videos that have been run through our model.</p>
						</header>
						
						<div class="content">
							<video width="350" height="263" src ="videos/cookies.mp4" autoplay muted controls loop></video>
							<br>
							<video width="350" height="263" src="videos/sports.mp4" autoplay muted controls loop></video>
							
						</div>
			
					</article>
				</div>
								
			</section>
			
			
			<!-- Footer -->
			<footer id="footer">
			</footer>
			

		

		<!-- Scripts -->
			<script src="js/jquery.min.js"></script>
			<script src="js/jquery.scrolly.min.js"></script>
			<script src="js/jquery.scrollex.min.js"></script>
			<script src="js/skel.min.js"></script>
			<script src="js/util.js"></script>
			<script src="js/main.js"></script>
			
			<script type="text/javascript">
				$('#video_form').submit(function() {
		        		$('#gif').show(); 
		        		return true;
		    		});
			</script>

	</body>
</html>