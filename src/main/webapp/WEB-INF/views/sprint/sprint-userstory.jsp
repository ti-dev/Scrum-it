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
		<link rel="stylesheet" href="<c:url value="/scripts/jquery.ui.datepicker/jquery.ui.datepicker.mobile.min.css" />" /> 
		<script src="<c:url value="/scripts/jquery-1.5.2/jquery-1.5.2.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-mobile-fixes/jquery-mobile-fixes.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-ui-1.8.11/jquery-ui-1.8.11.custom.min.js" />"></script>
		<script src="<c:url value="/scripts/json2/json2.min.js" />"></script>
		<script src="<c:url value="/scripts/scrumit.min.js" />"></script>
		<script src="<c:url value="/scripts/iscroll/iscroll-min.js" />"></script>
		<script src="<c:url value="/scripts/jquery.ui.datepicker/jquery.ui.datepicker.mobile.min.js" />"></script>
		<link rel="apple-touch-icon" href="<c:url value="/images/touch-icon-iphone.png" />" />
		<link rel="apple-touch-icon" size="72x72" href="<c:url value="/images/touch-icon-ipad.png" />" />
		<link rel="apple-touch-icon" size="114x114" href="<c:url value="/images/touch-icon-iphone4.png" />" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
	</head>
<body>
<div data-role="page" id="sprints" data-theme="b">
	<div class="mainheader" data-role="header" data-position="inline">
		<a data-theme="c" href="../../project/" data-icon="back" style="margin-left: 50px;margin-top:2px;" rel="external" title="<fmt:message key="scrum.backtoproject"/>"><fmt:message key="scrum.back"/></a>
		<img src="<c:url value="/images/scrumit-logo.png" />" alt="<fmt:message key="scrum.logo"/>" height="42" />
		<h1><fmt:message key="scrum.sprints"/></h1>
	</div>

	<div class="content" data-role="content">
		<div id="listing">
			<div data-role="header" data-theme="e">
				<h5><fmt:message key="scrum.listofsprints"/></h5>
			</div>
			<div id="wrapper-sprintlist">
				<div id="scroller-sprintlist">
					<ul id="sprintlist" data-role="listview" data-filter="true" data-split-theme="d">
						
					</ul>
				</div>
			</div>
			<div data-role="footer" class="ui-bar" data-theme="d" class="listingfooter">
				<a href="#" data-role="button" data-icon="delete" class="sprint-remove sprint-remove-button" title="<fmt:message key="scrum.deleteselectedsprint"/>"><fmt:message key="scrum.remove"/></a>
				<a href="#" data-role="button" data-icon="plus" class="sprint-add" title="<fmt:message key="scrum.createanewsprint"/>"><fmt:message key="scrum.add"/></a>
			</div>
		</div>
		<div id="listingdata">
			<div id="smalllisting">
				<div id="listshowhideuserstory">
					<div data-role="header" data-theme="e" data-position="fixed">
						<h5><fmt:message key="scrum.listofuserstories"/></h5>
					</div>
					<div id="wrapper-userstorylist">
						<div id="scroller-userstorylist">
							<ul id="userstorylist" data-role="listview" data-filter="true" data-split-theme="c">
								
							</ul>
						</div>
					</div>
					<div data-role="footer" class="ui-bar" data-theme="d" id="userstory-addremove-bar">
						<a href="#" data-role="button" data-icon="delete" class="userstory-remove userstory-remove-button" title="<fmt:message key="scrum.deleteselecteduserstory"/>"><fmt:message key="scrum.remove"/></a>
						<a href="#" data-role="button" data-icon="plus" class="userstory-add" title="<fmt:message key="scrum.createanewuserstory"/>"><fmt:message key="scrum.add"/></a>
					</div>
				</div>
			</div>
			<div id="wrapper-sprintuserstory">
			<div id="scroller-data">
				<div id="data">
					<div id="sprintform-update" class="sprint-remove-button">
						<h2><fmt:message key="scrum.sprint"/></h2>
						<form action="sprint/update" method="post" id="updateSprint">
							<div data-role="fieldcontain">
								<label for="label-sprint-id"><fmt:message key="scrum.id"/>:</label>
								<input type="text" name="id" id="label-sprint-id" readonly title="<fmt:message key="scrum.idofthesprint"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-slogan"><fmt:message key="scrum.slogan"/>:</label>
								<input type="text" name="slogan" id="label-slogan" data-theme="c" title="<fmt:message key="scrum.pleaseentersloganofthesprint"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-startdate"><fmt:message key="scrum.start"/>:</label>
								<input type="text" name="startDate" id="label-startdate" readonly title="<fmt:message key="scrum.startofthesprint"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-enddate"><fmt:message key="scrum.end"/>:</label>
								<input type="text" name="endDate" id="label-enddate" readonly title="<fmt:message key="scrum.endofthesprint"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.updatesprint"/>" /></div>
							</fieldset>
						</form>
					</div>
					<div id="sprintform-add">
						<h2><fmt:message key="scrum.createanewsprint"/></h2>
						<form action="add" method="post" id="addSprint">
							<div data-role="fieldcontain">
								<label for="label-add-slogan"><fmt:message key="scrum.slogan"/>:</label>
								<input type="text" name="slogan" id="label-add-slogan" data-theme="c" title="<fmt:message key="scrum.pleaseentersloganofthesprint"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-startdate"><fmt:message key="scrum.start"/>:</label>
								<input type="date" name="startDate" id="label-add-startdate" data-theme="c" placeholder="<fmt:message key="scrum.dateformat"/>" title="<fmt:message key="scrum.pleaseenterstartofthesprint"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-enddate"><fmt:message key="scrum.end"/>:</label>
								<input type="date" name="endDate" id="label-add-enddate" data-theme="c" placeholder="<fmt:message key="scrum.dateformat"/>" title="<fmt:message key="scrum.pleaseenterendofthesprint"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.createsprint"/>" /></div>
							</fieldset>
						</form>
					</div>
					<div id="userstoryform-update">
						<h2><fmt:message key="scrum.userstory"/></h2>
						<form action="userstory/update" method="post" id="updateUserstory">
							<div data-role="fieldcontain">
								<label for="label-userstory-id"><fmt:message key="scrum.id"/>:</label>
								<input type="text" name="id" id="label-userstory-id" readonly title="<fmt:message key="scrum.idoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-name"><fmt:message key="scrum.name"/>:</label>
								<input type="text" name="name" id="label-name" data-theme="c" title="<fmt:message key="scrum.pleaseenternameoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-priority"><fmt:message key="scrum.priority"/>:</label>
								<input type="number" name="priority" id="label-priority" data-theme="c" title="<fmt:message key="scrum.pleaseenterpriorityoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-creationdate"><fmt:message key="scrum.creation"/>:</label>
								<input type="text" name="creationDate" id="label-creationdate" readonly title="<fmt:message key="scrum.creationdateoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-estimatedsize"><fmt:message key="scrum.estimatedsize"/>:</label>
								<input type="number" name="estimatedSize" id="label-estimatedsize" data-theme="c" title="<fmt:message key="scrum.pleaseenterestsizeoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-acceptancetest"><fmt:message key="scrum.acctest"/>:</label>
								<input type="text" name="acceptanceTest" id="label-acceptancetest" data-theme="c" title="<fmt:message key="scrum.pleaseenteracctestoftheuserstory"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.updateuserstory"/>" /></div>	   
							</fieldset>
						</form>
					</div>
					<div id="userstoryform-add">
						<h2><fmt:message key="scrum.createanewuserstory"/></h2>
						<form action="userstory/add" method="post" id="addUserstory">
							<div data-role="fieldcontain">
								<label for="label-add-name"><fmt:message key="scrum.name"/>:</label>
								<input type="text" name="name" id="label-add-name" data-theme="c" title="<fmt:message key="scrum.pleaseenternameoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-priority"><fmt:message key="scrum.priority"/>:</label>
								<input type="number" name="priority" id="label-add-priority" data-theme="c" title="<fmt:message key="scrum.pleaseenterpriorityoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-estimatedsize"><fmt:message key="scrum.estimatedsize"/>:</label>
								<input type="number" name="estimatedSize" id="label-add-estimatedsize" data-theme="c" title="<fmt:message key="scrum.pleaseenterestsizeoftheuserstory"/>" />
							</div>
							<div data-role="fieldcontain">
								<label for="label-add-acceptancetest"><fmt:message key="scrum.acctest"/>:</label>
								<input type="text" name="acceptanceTest" id="label-add-acceptancetest" data-theme="c" title="<fmt:message key="scrum.pleaseenteracctestoftheuserstory"/>" />
							</div>
							<fieldset class="ui-grid-a">
								<div class="ui-block-a"><input type="reset" data-theme="c" value="<fmt:message key="scrum.cancel"/>" /></div>
								<div class="ui-block-b"><input type="submit" data-theme="b" value="<fmt:message key="scrum.adduserstory"/>" /></div>	   
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>

	<div class="mainfooter" data-role="footer">
		<img height="30" src="<c:url value="/images/bsgroupti-logo.png" />" alt="<fmt:message key="scrum.bsgroupti"/>" />
	</div>
</div>
</body>
<script type="text/javascript">
	var projectId = <c:out value="${projectid}"/>;
	var projectName = "<c:out value="${projectname}"/>";
	var selectedSprintId = Number.NaN;
	var selectedUserstoryId = Number.NaN;

	$(document).ready(function() {
		$("#listshowhideuserstory").hide();
		$("#listing form.ui-listview-filter").hide();
		$("#smalllisting form.ui-listview-filter").hide();

		$("#sprintform-add").hide();
		$("#userstoryform-add").hide();
		$("#sprintform-update").hide();
		$("#userstoryform-update").hide();

		$(".sprint-remove-button").hide();
		$(".userstory-remove-button").hide();

		$("#userstory-addremove-bar").hide();

		$("#addSprint").submit(addSprint);
		$("#addUserstory").submit(addUserstory);
		$("#updateSprint").submit(updateSprint);
		$("#updateUserstory").submit(updateUserstory);

		setDimensionsForSprintUserstory();
		$(window).resize(setDimensionsForSprintUserstory);
		window.onorientationchange = setDimensionsForSprintUserstory;

		$(".mainheader h1").html("Project: <strong>"+projectName+"</strong>");

		getAllSprintsByProjectId(projectId);

		$('.sprint-action').live('click', selectSprint);
		$('.sprint-add').live('click', addSprintAction);
		$('.sprint-remove').live('click', removeSprint);
		$('.userstory-action').live('click', selectUserstory);
		$('.userstory-add').live('click', addUserstoryAction);
		$('.userstory-remove').live('click', removeUserstory);

		myScroll1 = new iScroll('scroller-sprintlist');
		myScroll2 = new iScroll('scroller-userstorylist');
		myScroll3 = new iScroll('scroller-data');

		$(".hasDatepicker").hide();
		$("#label-add-startdate").focus(function() {
			$(".label-add-startdate").show();
			myScroll3.refresh();
		});
		$("#label-add-enddate").focus(function() {
			$(".label-add-enddate").show();
			myScroll3.refresh();
		});
		$(".ui-datepicker-calendar a").live("click", function() {
			$(".hasDatepicker").hide();
			myScroll3.refresh();
		});
	});
	function updateScroll() {
		myScroll3.refresh();
	}
</script>
</html>