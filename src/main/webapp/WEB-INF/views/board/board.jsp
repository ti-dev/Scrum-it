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
		<!--[if IE]><script language="javascript" type="text/javascript" src="<c:url value="/scripts/jqPlot-0.9.7/excanvas.min.js" />"></script><![endif]-->
		<script src="<c:url value="/scripts/jquery-1.5.2/jquery-1.5.2.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-mobile-fixes/jquery-mobile-fixes.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery.mobile-1.0a4.1/jquery.mobile-1.0a4.1.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-ui-1.8.11/jquery-ui-1.8.11.custom.min.js" />"></script>
		<script src="<c:url value="/scripts/json2/json2.min.js" />"></script>
		<script src="<c:url value="/scripts/jquery-ui-ipad/jquery.ui.ipad.min.js" />"></script>
		<script language="javascript" type="text/javascript" src="<c:url value="/scripts/jqPlot-1.0/jquery.jqplot.min.js" />"></script>
		<link rel="stylesheet" type="text/css" href="<c:url value="/scripts/jqPlot-1.0/jquery.jqplot.min.css" />" />
		<script language="javascript" type="text/javascript" src="<c:url value="/scripts/jqPlot-1.0/plugins/jqplot.categoryAxisRenderer.min.js" />"></script>
		<script language="javascript" type="text/javascript" src="<c:url value="/scripts/jqPlot-1.0/plugins/jqplot.cursor.min.js" />"></script>
		<script language="javascript" type="text/javascript" src="<c:url value="/scripts/jqPlot-1.0/plugins/jqplot.highlighter.min.js" />"></script>
		<script src="<c:url value="/scripts/scrumit.min.js" />"></script>
		<link rel="apple-touch-icon" href="<c:url value="/images/touch-icon-iphone.png" />" />
		<link rel="apple-touch-icon" size="72x72" href="<c:url value="/images/touch-icon-ipad.png" />" />
		<link rel="apple-touch-icon" size="114x114" href="<c:url value="/images/touch-icon-iphone4.png" />" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
	</head>
<body>
<div data-role="page" id="scrumboard" data-theme="b">
	<div class="mainheader" data-role="header" data-position="inline">
		<a data-theme="c" href="../../../sprint/<c:out value="${projectid}"/>/" data-icon="back" style="margin-left:50px;margin-top:2px;" rel="external" title="<fmt:message key="scrum.backtosprint"/>"><fmt:message key="scrum.back"/></a>
		<img src="<c:url value="/images/scrumit-logo.png" />" alt="<fmt:message key="scrum.logo"/>" height="42" />
		<h1><fmt:message key="scrum.board"/></h1>
	</div>
				
	<div class="content" data-role="content">
		<div id="board">
			<div id="userstories">
				
			</div>
			<div id="open">
				<div data-role="header" data-theme="d">
					<h1><fmt:message key="scrum.open"/></h1>
				</div>
			</div>
			<div id="wip">
				<div data-role="header" data-theme="d">
					<h1><fmt:message key="scrum.inprogress"/></h1>
				</div>
			</div>
			<div id="done">
				<div data-role="header" data-theme="d">
					<h1><fmt:message key="scrum.done"/></h1>
				</div>
			</div>
		</div>
	</div>

	<div class="mainfooter" data-role="footer">
		<a style="position:relative;top:8px;left:1%;" href="#" class="burndownchart-show" data-role="button" title="<fmt:message key="scrum.burndownchart"/>"><fmt:message key="scrum.burndownchart"/></a>
		<img height="30" src="<c:url value="/images/bsgroupti-logo.png" />" alt="<fmt:message key="scrum.bsgroupti"/>" />
	</div>
</div>
</body>
<script type="text/javascript">
	var projectId = <c:out value="${projectid}"/>;
	var projectName = "<c:out value="${projectname}"/>";
	var sprintId = <c:out value="${sprintid}"/>;
	var sprintSlogan = "<c:out value="${sprintslogan}"/>";
	var statusId = new Array("open", "wip", "done");
	var colors = new Array("yellow", "blue", "red", "green", "white", "orange");
	var zIndexUserstory = 1000;
	var zIndexTask = 1000;

	$(document).ready(function() {
		setBoardDimensions();
		$(window).resize(setBoardDimensions);
		window.onorientationchange = setBoardDimensions;

		$(".mainheader h1").html("Project: <strong>"+projectName+"</strong> - Sprint: <strong>"+sprintSlogan+"</strong>");

		getAllUserstories();

		$('#open,#wip,#done').droppable({
			scope: "items", 
			drop: function(event, ui) {
				var pos = ui.draggable.offset(), dPos = $(this).offset();
				topProzent = Math.round(((pos.top - dPos.top) / $("#"+$(this).attr('id')).height()) * 100);
				leftProzent = Math.round(((pos.left - dPos.left) / $("#"+$(this).attr('id')).width()) * 100);
				taskObject = {
					id: ui.draggable.data("taskid"), 
					xCoord: leftProzent, 
					yCoord: topProzent, 
					status: statusId.indexOf($(this).attr('id'))
				};
				var draggedFrom = ui.draggable.data('origin');
				var attrId = $(this).attr('id');
				ui.draggable.data('origin', $(this).attr('id'));
				$.postJSON("../../task/updatecoord/"+sprintId+"/", taskObject, function(data) {
					if($("#burndownchart").is(":visible")) {
						if (draggedFrom == "done" && statusId.indexOf(attrId) < 2) {
							loadBurnDownChart();
						} else {
							if (statusId.indexOf(attrId) == 2 && (draggedFrom == "open" || draggedFrom == "wip")) {
								loadBurnDownChart();
							}
						}
					}
				});
			}
		});
		$('.content').append('<div id="burndownchart"></div>');
		$('#burndownchart').hide();
		$('.burndownchart-show').live('click', toggleShow);
		$('#burndownchart').draggable({
			containment: ".content", 
			scroll: false
		});
	});

	function getAllUserstories() {
		var i = 0;
		$.getJSON("../../alluserstories/"+sprintId+"/", function(data) {
			$.each(data.sort(sortById), function(id, userstory) {
				topPx = Math.round((userstory.yCoord / 100) * $("#userstories").height());
				leftPx = Math.round((userstory.xCoord / 100) * $("#userstories").width());
				$('#userstories').append('<div class="userstory-note note-'+colors[(i%colors.length)]+'" data-color="'+colors[(i%colors.length)]+'" style="top:'+topPx+'px;left:'+leftPx+'px;" data-userstoryid="'+userstory.id+'"><textarea class="userstory-edit">'+userstory.name+'</textarea><div class="overlay"></div><div class="timestamp">&nbsp;</div><div class="addbutton"></div></div>');
				getAllTasksByUserstoryId(userstory.id, colors[(i%colors.length)]);
				i++;
			});
			$('.addbutton').click(function() {
				topProzent = Math.round(($(this).parent().position().top / $("#open").height()) * 100);
				taskObject = {
					description: "New task...", 
					xCoord: 0, 
					yCoord: topProzent, 
					status: 0, 
					duration: 8
				};
				color = $(this).parent().data("color");
				$.postJSON("../../add/task/"+$(this).parent().data("userstoryid")+"/"+sprintId+"/", taskObject, function(data) {
					taskDuration += data.duration;
					if($("#burndownchart").is(":visible")) {
						loadBurnDownChart();
					}
					topPx = Math.round((data.yCoord / 100) * $("#open").height());
					leftPx = Math.round((data.xCoord / 100) * $("#open").width());
					$('#open').append('<div class="task-note note-'+color+'" style="top:'+topPx+'px;left:'+leftPx+'px;" data-taskid="'+data.id+'"><textarea class="task-edit">'+data.description+'</textarea><div class="overlay"></div><div class="timestamp">'+dateFromTimestamp(data.creationDate)+'</div></div>');
					$(".task-note").draggable({
						scroll: false, 
						revert: "invalid", 
			            scope: "items", 
						start: function(event, ui) {
							$(this).css("z-index", ++zIndexTask);
						},
						stop: function(event, ui) {
							$(this).css("z-index", ++zIndexTask);
						}
					}).each(function(i, e) {
					    $(e).data('origin', $(e).parent().attr('id'));
					  });
					$('.task-edit').blur(function() {
						taskObject = {
							id: $(this).parent().data("taskid"), 
							description: $(this).val()
						};
						$.postJSON("../../task/updatedescription/", taskObject);
					});
				});
			});
			$(".userstory-note").draggable({
				containment: "#userstories", 
				scroll: false, 
				zIndex: zIndexUserstory++, 
				start: function(event, ui) {
					$(this).css("z-index", ++zIndexUserstory);
				},
				stop: function(event, ui) {
					topProzent = Math.round(($(this).position().top / $("#userstories").height()) * 100);
					leftProzent = Math.round(($(this).position().left / $("#userstories").width()) * 100);
					userstoryObject = {
						id: $(this).data("userstoryid"), 
						xCoord: leftProzent, 
						yCoord: topProzent
					};
					$.postJSON("../../userstory/updatecoord/", userstoryObject);
				}
			});
			$('.userstory-edit').blur(function() {
				userstoryObject = {
					id: $(this).parent().data("userstoryid"), 
					name: $(this).val()
				};
				$.postJSON("../../userstory/updatename/", userstoryObject);
			});
		});
	}

	function getAllTasksByUserstoryId(userstoryId, color) {
		taskDuration = 0;
		$.getJSON("../../alltasks/"+userstoryId+"/", function(data) {
			$.each(data, function(id, task) {
				if (task.status == 0) {
					divAreaId = "#open";
				} else if (task.status == 1) {
					divAreaId = "#wip";
				} else {
					divAreaId = "#done";
				}
				taskDuration = taskDuration + task.duration;
				$(divAreaId).append('<div class="task-note note-'+color+'" style="top:'+task.yCoord+'%;left:'+task.xCoord+'%;" data-taskid="'+task.id+'"><textarea class="task-edit">'+task.description+'</textarea><div class="overlay"></div><div class="timestamp">'+dateFromTimestamp(task.creationDate)+'</div></div>');
				$(".task-note").css("z-index", ++zIndexTask);
				$(".task-note").draggable({
					scroll: false, 
					revert: "invalid", 
		            scope: "items", 
					start: function(event, ui) {
						$(this).css("z-index", ++zIndexTask);
					},
					stop: function(event, ui) {
						$(this).css("z-index", ++zIndexTask);
					}
				}).each(function(i, e) {
				    $(e).data('origin', $(e).parent().attr('id'));
				  });
				$('.task-edit').blur(function() {
					taskObject = {
						id: $(this).parent().data("taskid"), 
						description: $(this).val()
					};
					$.postJSON("../../task/updatedescription/", taskObject);
				});
			});
		});
	}
</script>
</html>