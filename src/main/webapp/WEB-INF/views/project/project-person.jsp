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
		<script src="<c:url value="/scripts/json2/json2.min.js" />"></script>
		<script src="<c:url value="/scripts/scrumit.min.js" />"></script>
		<script src="<c:url value="/scripts/iscroll/iscroll-min.js" />"></script>
		<link rel="apple-touch-icon" href="<c:url value="/images/touch-icon-iphone.png" />" />
		<link rel="apple-touch-icon" size="72x72" href="<c:url value="/images/touch-icon-ipad.png" />" />
		<link rel="apple-touch-icon" size="114x114" href="<c:url value="/images/touch-icon-iphone4.png" />" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
	</head>
<body>
<div data-role="page" id="projects" data-theme="b">

	<div class="mainheader" data-role="header" data-position="inline">
	
	<a data-theme="c" href="../" data-icon="back" style="margin-left:50px;margin-top:2px;" rel="external" title="back to the homepage">Back to homepage</a>
    <img src="<c:url value="/images/scrumit-logo.png" />" alt="<fmt:message key="scrum.logo"/>" height="42" />
	<h1><fmt:message key="scrum.projects"/></h1>
	</div>

	<div class="content" data-role="content">
		<div id="listing">
			<div data-role="header" data-theme="e">
				<h5><fmt:message key="scrum.listofprojects"/></h5>
			</div>
			<div id="wrapper-projectlist">
				<div id="scroller-projectlist">
					<ul id="projectlist" data-role="listview" data-filter="true" data-split-theme="d">
						
					</ul>
				</div>
			</div>
			<div data-role="footer" class="ui-bar" data-theme="d" class="listingfooter">
				<a href="#" data-role="button" data-icon="delete" class="project-remove project-remove-button" title="<fmt:message key="scrum.deleteselectedproject"/>"><fmt:message key="scrum.remove"/></a>
				<a href="#" data-role="button" data-icon="plus" class="project-add" title="<fmt:message key="scrum.createanewproject"/>"><fmt:message key="scrum.add"/></a>
			</div>
		</div>
		<div id="listingdata">
			<div id="smalllisting">
				<div id="listshowhideperson">
					<div data-role="header" data-theme="e" data-position="fixed">
						<h5><fmt:message key="scrum.listofpersons"/></h5>
					</div>
					<div id="wrapper-personlist">
						<div id="scroller-personlist">
        					<ul id="personlist" data-role="listview" data-filter="true" data-split-theme="c">
								
							</ul>
    					</div>
					</div>
					<div data-role="footer" class="ui-bar" data-theme="d" id="person-addremove-bar">
						<a href="#" data-role="button" data-icon="delete" class="person-remove person-remove-button" title="<fmt:message key="scrum.deleteselectedperson"/>"><fmt:message key="scrum.remove"/></a>
						<a href="#" data-role="button" data-icon="plus" class="person-add" title="<fmt:message key="scrum.createanewperson"/>"><fmt:message key="scrum.add"/></a>
					</div>
				</div>
			</div>
			<div id="wrapper-projectperson">
				<div id="data">
					<div id="projectform-update" class="project-remove-button">
						<h2><fmt:message key="scrum.project"/></h2>
						<form action="project/update" method="post" id="updateProject">
							<div data-role="fieldcontain">
								<label for="label-project-id"><fmt:message key="scrum.id"/>:</label>
								<input type="text" name="id" id="label-project-id" readonly title="<fmt:message key="scrum.idoftheproject"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-name"><fmt:message key="scrum.name"/>:</label>
								<input type="text" name="name" id="label-name" data-theme="c" title="<fmt:message key="scrum.pleaseenternameoftheproject"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-description"><fmt:message key="scrum.description"/>:</label>
								<input type="text" name="description" id="label-description" data-theme="c" title="<fmt:message key="scrum.pleaseenterdescriptionoftheproject"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-creationdate"><fmt:message key="scrum.creation"/>:</label>
								<input type="text" name="creationDate" id="label-creationdate" readonly title="<fmt:message key="scrum.creationdateoftheproject"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.updateproject"/>" /></div>
							</fieldset>
						</form>
					</div>
					<div id="projectform-add">
						<h2><fmt:message key="scrum.createanewproject"/></h2>
						<form action="project/add" method="post" id="addProject">
							<div data-role="fieldcontain">
								<label for="label-add-name"><fmt:message key="scrum.name"/>:</label>
								<input type="text" name="name" id="label-add-name" data-theme="c" title="<fmt:message key="scrum.pleaseenternameoftheproject"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-description"><fmt:message key="scrum.description"/>:</label>
								<input type="text" name="description" id="label-add-description" data-theme="c" title="<fmt:message key="scrum.pleaseenterdescriptionoftheproject"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.createproject"/>" /></div>
							</fieldset>
						</form>
					</div>
					<div id="personform-update">
						<h2><fmt:message key="scrum.person"/></h2>
						<form action="project/person/update" method="post" id="updatePerson">
							<div data-role="fieldcontain">
								<label for="label-person-id"><fmt:message key="scrum.id"/>:</label>
								<input type="text" name="id" id="label-person-id" readonly title="<fmt:message key="scrum.idoftheperson"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-firstname"><fmt:message key="scrum.firstname"/>:</label>
								<input type="text" name="firstName" id="label-firstname" data-theme="c" title="<fmt:message key="scrum.pleaseenterfirstnameoftheperson"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-lastname"><fmt:message key="scrum.lastname"/>:</label>
								<input type="text" name="lastName" id="label-lastname" data-theme="c" title="<fmt:message key="scrum.pleaseenterlastnameoftheperson"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-email"><fmt:message key="scrum.email"/>:</label>
								<input type="text" name="email" id="label-email" data-theme="c" title="<fmt:message key="scrum.pleaseenteremailoftheperson"/>" type="email" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.updateperson"/>" /></div>	   
							</fieldset>
						</form>
					</div>
					<div id="personform-add">
						<h2><fmt:message key="scrum.createanewperson"/></h2>
						<form action="project/person/add" method="post" id="addPerson">
							<div data-role="fieldcontain">
								<label for="label-add-firstname"><fmt:message key="scrum.firstname"/>:</label>
								<input type="text" name="firstName" id="label-add-firstname" data-theme="c" title="<fmt:message key="scrum.pleaseenterfirstnameoftheperson"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-lastname"><fmt:message key="scrum.lastname"/>:</label>
								<input type="text" name="lastName" id="label-add-lastname" data-theme="c" title="<fmt:message key="scrum.pleaseenterlastnameoftheperson"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-email"><fmt:message key="scrum.email"/>:</label>
								<input type="email" name="email" id="label-add-email" data-theme="c" title="<fmt:message key="scrum.pleaseenteremailoftheperson"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.addperson"/>" /></div>	   
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="mainfooter" data-role="footer">
		<img height="30" src="<c:url value="/images/bsgroupti-logo.png" />" alt="<fmt:message key="scrum.bsgroupti"/>" />
	</div>
</div>
<script type="text/javascript">
	var selectedProjectId = Number.NaN;
	var selectedPersonId = Number.NaN;

	$(document).ready(function() {
		$("#listshowhideperson").hide();
		$("#listing form.ui-listview-filter").hide();
		$("#smalllisting form.ui-listview-filter").hide();

		$("#projectform-add").hide();
		$("#personform-add").hide();
		$("#projectform-update").hide();
		$("#personform-update").hide();

		$(".project-remove-button").hide();
		$(".person-remove-button").hide();

		$("#person-addremove-bar").hide();

		$("#addProject").submit(addProject);
		$("#addPerson").submit(addPerson);
		$("#updateProject").submit(updateProject);
		$("#updatePerson").submit(updatePerson);

		setDimensionsForProjectPerson();
		$(window).resize(setDimensionsForProjectPerson);
		window.onorientationchange = setDimensionsForProjectPerson;

		getAllProjects();

		$('.project-action').live('click', selectProject);
		$('.project-add').live('click', addProjectAction);
		$('.project-remove').live('click', removeProject);
		$('.person-action').live('click', selectPerson);
		$('.person-add').live('click', addPersonAction);
		$('.person-remove').live('click', removePerson);

		myScroll1 = new iScroll('scroller-projectlist');
		myScroll2 = new iScroll('scroller-personlist');
		myScroll3 = new iScroll('data');
	});	
</script>
</body>
</html>