
<%@ page import="assignment.Opportunities" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'opportunities.label', default: 'Opportunities')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-opportunities" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-opportunities" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="applications" title="${message(code: 'opportunities.applications.label', default: 'Applications')}" />
					
						<g:sortableColumn property="companyname" title="${message(code: 'opportunities.companyname.label', default: 'Companyname')}" />
					
						<g:sortableColumn property="jobtitle" title="${message(code: 'opportunities.jobtitle.label', default: 'Jobtitle')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'opportunities.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${opportunitiesInstanceList}" status="i" var="opportunitiesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${opportunitiesInstance.id}">${fieldValue(bean: opportunitiesInstance, field: "applications")}</g:link></td>
					
						<td>${fieldValue(bean: opportunitiesInstance, field: "companyname")}</td>
					
						<td>${fieldValue(bean: opportunitiesInstance, field: "jobtitle")}</td>
					
						<td>${fieldValue(bean: opportunitiesInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${opportunitiesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
