<%
boolean isDemo = false;
if (request.getServerName().indexOf("demo") != -1) {
	isDemo = true;
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title><fmt:message key="scrum.title"/></title>
		<link rel="shortcut icon" href="<c:url value="/images/favicon.ico" />" type="image/x-icon">
		<link rel="stylesheet" href="<c:url value="/scripts/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.css" />">
		<link rel="stylesheet" href="<c:url value="/styles/layout.css" />">
		<script src="<c:url value="/scripts/jquery-1.5.2/jquery-1.5.2.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-mobile-fixes/jquery-mobile-fixes.js" />"></script>
		<script src="<c:url value="/scripts/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js" />"></script>
		<link rel="apple-touch-icon" href="<c:url value="/images/touch-icon-iphone.png" />" />
		<link rel="apple-touch-icon" size="72x72" href="<c:url value="/images/touch-icon-ipad.png" />" />
		<link rel="apple-touch-icon" size="114x114" href="<c:url value="/images/touch-icon-iphone4.png" />" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
	</head>
<body>
<div data-role="page" id="welcome" data-theme="b">
	<div class="mainheader" data-role="header" data-position="inline" style="height:50px;padding-top:14px;text-align:center;">
		<h1><fmt:message key="scrum.title"/></h1>
		<div style="float:left;">
			<img src="<c:url value="/images/Scrum-it-Logo-klein.png" />" alt="<fmt:message key="scrum.title"/>" width="55"/>
		</div>
		<% if (!isDemo) { %>
		<div style="float:right;">
			<img src="<c:url value="/images/logo-bosw2011.jpg" />" alt="Logo: Best of Swiss Web 2011" width="50">
		</div>
		<% } %>
	</div>

	<div class="content-welcome" data-role="content" data-theme="d"> 
		<div class="left">
			<div data-role="header" data-theme="e">
				<h2>Willkommen an Board!</h2>
			</div>
			<div data-role="content" style="padding:2%;">
				<h3>Scrum-it - Digitales Scrum Board mit Touch-Technologie</h3>
				<h4>Was ist Scrum-it?</h4>
				<p>
					<em>Scrum-it</em> ist das <em>digitale Scrum Board mit Touch-Technologie</em>. Es dient 
					als Hilfsmittel für das Management von agilen Projekten und schlägt eine Brücke zwischen 
					digitaler und physischer Welt. Dadurch werden alle Vorteile summiert (Archivierung, 
					verteilte Teams, Beibehaltung der Kommunikationsplattform Scrum Board).
				</p>
				<p>
					Diese bisher einmalige Verknüpfung erspart Ihnen und Ihrem Scrum-Team Zeit und Kosten. Der 
					Fokus kann stärker auf das eigentliche Ziel gelegt werden, die Entwicklung hochqualitativer 
					Software.
				</p>
				<h4>Plattform-Unabhängigkeit / Touch-Fähigkeit</h4>
				<p>
					Scrum-it kann auf allen Geräten verwendet werden, mittels Maus / Tastatur und über 
					Fingereingaben (Multi-Touch), auf denen ein Webbrowser läuft.
					<div style="float:left;width:50%;">
						<ul style="margin:0;list-style-type:square;padding-left:28px;">
							<li>Tablets (z.B. Apple iPad)</li>
							<li>Desktop-Bildschirme</li>
							<li>Large Format Displays</li>
							<li>Multi-Touch Displays jeder Grösse</li>
						</ul>
					</div>
					<div style="float:right;width:50%;text-align:center;padding-top:8px;">
						<img src="<c:url value="/images/SupportedBrowserLogo.png" />" /><br />
						<span style="font-size:x-small;"><i>Hinweis</i>: Internet Explorer ab Version 9.</span>
					</div>
				</p>
				<div style="clear:both;">&nbsp;</div>
				<a href="project/" data-role="button" data-theme="d" rel="external">Zur Live-Demo</a> 
			</div>
		</div>

		<div class="right">
			<div data-role="header" data-theme="e">
				<h5>Linkliste</h5>
			</div>
			<ul data-role="listview">
				<% if (!isDemo) { %>
				<li><a href="http://www.bsgroupti.ch/scrum-it/Scrum-it-Presentation.pdf">Präsentation</a></li>
				<% } %>
				<li><a href="http://www.youtube.com/watch?v=zfcZF1EeY7Q">Benutzerführungsvideo</a></li>
				<li><a href="http://www.youtube.com/watch?v=Hp9wMQkmYDw">Film: Scrum-it Story</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it.html">Webseite zu Scrum-it</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it/Scrum-it-Flyer.pdf">Flyer über Scrum-it</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it/Scrum-it-SwissITMagazineArtikel.pdf">Magazin-Artikel</a></li>
			</ul>
			<iframe src="http://www.facebook.com/plugins/likebox.php?href=http%3A%2F%2Fwww.facebook.com%2F%23%21%2Fpages%2FScrum-it%2F161740420538807&amp;width=200&amp;colorscheme=light&amp;show_faces=false&amp;stream=false&amp;header=false&amp;height=62" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:100%; height:62px; background-color:rgb(240, 240, 240);padding-top:35px;" allowTransparency="true"></iframe>
		</div>
	</div>

	<div class="mainfooter" data-role="footer">
		<img height="30" src="<c:url value="/images/bsgroupti-logo.png" />" alt="<fmt:message key="scrum.bsgroupti"/>" />
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	setDimensions();
	$(window).resize(setDimensions);
	window.onorientationchange = setDimensions;
});
function setDimensions() {
	$('.content-welcome').css({height: ($(".left").outerHeight()+3)});
}

var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
try{
var pageTracker = _gat._getTracker("UA-20612429-1");
pageTracker._trackPageview();
} catch(err) {}

</script>
</body>
</html>