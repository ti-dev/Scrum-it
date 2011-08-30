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
		<script src="<c:url value="/scripts/jquery-mobile-fixes/jquery-mobile-fixes.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js" />"></script>
		<link rel="apple-touch-icon" href="<c:url value="/images/touch-icon-iphone.png" />" />
		<link rel="apple-touch-icon" size="72x72" href="<c:url value="/images/touch-icon-ipad.png" />" />
		<link rel="apple-touch-icon" size="114x114" href="<c:url value="/images/touch-icon-iphone4.png" />" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<style type="text/css">
			.landscape, .landscape .ui-page {
				min-height: 320px;
			}
		</style>
	</head>
<body>
<div data-role="page" id="welcome" data-theme="b">
	<div class="mainheader" data-role="header" data-position="inline" style="height:50px;padding-top:14px;text-align:center;">
		<h1><fmt:message key="scrum.title"/></h1>
		<div style="float:left;">
			<img src="<c:url value="/images/Scrum-it-Logo-klein.png" />" alt="<fmt:message key="scrum.title"/>" width="55"/>
		</div>
		<div style="float:right;">
			<img src="<c:url value="/images/BOSW11in_B.png" />" alt="Logo: Best of Swiss Web 2011 Bronze Award" width="50">
		</div>
	</div>

	<div class="content-welcome" data-role="content" data-theme="d"> 
		<div class="left">
			<div data-role="header" data-theme="e">
				<h2>Welcome on board!</h2>
			</div>
			<div data-role="content" style="padding:2%;">
				<h3>Scrum-it - a digital scrum board with touch technology</h3>
				<h4>What is Scrum-it?</h4>
				<p>
					<em>Scrum-it</em> is the <em>digital scrum board with touch technology</em>. It supports all your management tasks in agile projects
					and combines advantages of both worlds, digital and the real world. You get all benefits in one package (archiving, 
					distributed teams, keeping the communication platform Scrum Board).
				</p>
				<p>
					This unique application saves time and cost for you and your team. Because of that your focus remains on the goal developing
					high-quality software.
				</p>
				<h4>Platform independence / touch capability</h4>
				<p>
					Scrum-it supports multitouch and mouse/keyboard based devices, because of its cross browser compatibility and platform independence.
					<div style="float:left;width:50%;">
						<ul style="margin:0;list-style-type:square;padding-left:28px;">
							<li>Tablets (Apple iPad)</li>
							<li>Desktop screens</li>
							<li>Large Format Displays</li>
							<li>Multitouch displays</li>
						</ul>
					</div>
					<div style="float:right;width:50%;text-align:center;padding-top:8px;">
						<img src="<c:url value="/images/SupportedBrowserLogo.png" />" /><br />
						<span style="font-size:x-small;"><i>Note</i>: Internet Explorer version 9</span>
					</div>
				</p>
				<div style="clear:both;">&nbsp;</div>
				<h4>Scrum-it live demo</h4>
				<p>
					<div style="float:right;width:25%;text-align:right;">
						<a href="project/" data-role="button" data-theme="d" rel="external">
						<img src="<c:url value="/images/Play_button_small.png"/>" width="35"/><br/><span align="center">live demo</span></a> 
					</div>
					<div style="float:left;width:75%">
						Expire the feeling on a web based Scrum Board with touch technology. Take a tour through our 
						live demo and learn the innovative art of user interface, enabled with jQuery Mobile.
					</div>
				</p>
				<div style="clear:both;">&nbsp;</div>
				<h4>Get Scrum-it for free</h4>
				<p>
					<div style="float:left;width:25%;">
						<a href="http://sourceforge.net/projects/scrum-it/" data-role="button" data-theme="d" target="blank">
						<img src="<c:url value="/images/download.png" />" width="35"/>
						<br/><span align="center">get it</span></a>
					</div>
					<div style="float:right;width:75%">
						SourceForge is a centralized web based project repository. You can download a free version of Scrum-it without any limitations. Installation incstructions, 
						an issue tracker and a lot of more information can be found there. If you want new features, feel free to contact us.
					</div> 
				</p>
				<div style="clear:both;">&nbsp;</div>
				<h4>Bronze award "Best of Swiss Web 2011"</h4>
				<p>
					<div style="float:right;width:20%;text-align:right;">
						<img src="<c:url value="/images/BOSW11in_B_small.png" />" width="100" />
					</div>
					<div style="float:left;width:80%">
						Best of Swiss Web is yearly awarded price for leading technologies in the web sector. Scrum-it got the bronze award in the category 
						Innovation for the modern and innovative way to handle agile project management with Scrum.
					</div> 
				</p>
			</div>
		</div>

		<div class="right">
			<div data-role="header" data-theme="e">
				<h5>link list</h5>
			</div>
			<ul data-role="listview">
				<li><a href="http://www.youtube.com/watch?v=zfcZF1EeY7Q">User video</a></li>
				<li><a href="http://www.youtube.com/watch?v=Hp9wMQkmYDw">Scrum-it Story - Movie</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it.html">Scrum-it webpage</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it/Scrum-it-Flyer.pdf">Flyer about Scrum-it</a></li>
				<li><a href="http://www.bsgroupti.ch/scrum-it/Scrum-it-SwissITMagazineArtikel.pdf">magazine article</a></li>
				<li><a href="http://sourceforge.net/projects/scrum-it/">SourceForge</a></li>
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