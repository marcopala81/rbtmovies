<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="header.jsp" %>
    <div class="centered">
	    <form:form method="POST" modelAttribute="mcForm">
	        <table>
	            <tr>
	                <td>Enter first movie title:</td>
	                <td><form:input path="movieTitle1" /></td>
	                <td><form:errors path="movieTitle1" cssClass="error"/></td>
	            </tr>
	            <tr>
	                <td>Enter second movie title:</td>
	                <td><form:input path="movieTitle2" /></td>
	                <td><form:errors path="movieTitle2" cssClass="error"/></td>
	            </tr>
	            <tr>
	                <td><input type="submit" name="submit" value="Submit"></td>
	            </tr>
	            <tr>
	        </table>
	    </form:form>
    </div>
<%@ include file="footer.jsp" %>