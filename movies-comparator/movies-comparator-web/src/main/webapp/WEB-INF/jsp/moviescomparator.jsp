<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="header.jsp" %>
    <div class="centered">
	    <form:form method="POST" modelAttribute="moviesCompForm">
	        <table>
                <thead class="centered">
                <tr>
                    <th class="main" colspan="2">Choose two movie titles to know which is better rated over the web!</th>
                </tr>
                </thead>	            
	            <tr>
	                <td class="label">Enter first movie title:</td>
	                <td><form:input cssClass="compareTitle" path="movieTitle1" />
	                    <form:errors path="movieTitle1" cssClass="error" element="div"/></td>
	            </tr>
	            <tr>
	                <td class="label">Enter second movie title:</td>
	                <td><form:input cssClass="compareTitle" path="movieTitle2" />
	                    <form:errors path="movieTitle2" cssClass="error" element="div"/></td>
	            </tr>
	            <tr>
	                <td colspan="2" class="right"><input type="submit" name="submit" value="Submit"></td>
	            </tr>
	        </table>
	    </form:form>	    
    </div>
<%@ include file="footer.jsp" %>