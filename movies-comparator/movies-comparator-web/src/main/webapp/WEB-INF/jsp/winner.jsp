<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="header.jsp" %>
    <div class="outer centered">
        <div class="inner">        
            <h2>${moviesCompForm.message}&nbsp;&nbsp;<c:if test="${moviesCompForm.winnerFound}">${moviesCompForm.winnerMovie.title}!!!</c:if></h2>
            <br>
            <br>
            <form:form method="POST" servletRelativeAction="update" modelAttribute="moviesCompForm">
	            <table>
	                <thead class="centered">
	                <tr class="main">
	                    <th class="id">OMBd ID</th>
	                    <th class="title">Title</th>
	                    <th class="rating">Rating</th>
	                    <th class="favourite">Favourite</th>
	                </tr>
	                </thead>                
	                <tr>
	                    <td class="centered courierBig">${moviesCompForm.movie1.imdbId}</td>
	                    <td>${moviesCompForm.movie1.title}</td>
	                    <td class="right">
	                       <form:input id="rating1" cssClass="rating" path="movie1.rating" maxlength="4"
	                                   onkeypress="return validateRating(event, this.id);"
	                                   onChange="return parseFloatRating(this.id);"/>
	                    </td>
                        <td>
                            <form:checkbox id="checkbox1" cssClass="centerd" path="movie1.favourite" onChange="return switchOther(this.id, 'checkbox2');" />
                        </td>
                        <!-- Hidden fields to propagate -->
                        <form:hidden path="movie1.id" />
                        <form:hidden path="movie1.imdbId" />
                        <form:hidden path="movie1.title" />	                    
	                    <form:hidden id="oldRating1" path="movie1.rating" />
	                    <form:hidden id="changedFlag1" path="movie1.changed" />
	                </tr>
	                <tr>
	                    <td class="centered courierBig">${moviesCompForm.movie2.imdbId}</td>
	                    <td>${moviesCompForm.movie2.title}</td>
	                    <td class="right">
	                       <form:input id="rating2" cssClass="rating" path="movie2.rating" maxlength="4"
	                                   onkeypress="return validateRating(event, this.id);" 
	                                   onChange="return parseFloatRating(this.id);" />
	                    </td>
                        <td>
                            <form:checkbox id="checkbox2" cssClass="centerd" path="movie2.favourite" onChange="return switchOther(this.id, 'checkbox1');" />
                        </td>
	                    <!-- Hidden fields to propagate -->
                        <form:hidden path="movie2.id" />
                        <form:hidden path="movie2.imdbId" />
                        <form:hidden path="movie2.title" />
                        <form:hidden id="oldRating2" path="movie2.rating" />
                        <form:hidden id="changedFlag2" path="movie2.changed" />
	                </tr>
	                <tr>
	                    <td colspan="4" class="right">
	                       You can change a movie rating and even mark your favourite one >>>&nbsp;&nbsp;
	                       <input type="submit" name="submit" value="Update">
	                    </td>	                    
	                </tr>                
	            </table>
            </form:form>
	        <br>
	        <br>
	        <h3><a href="moviescomparator">...or just compare two new movies!</a></h3>
        </div>
    </div>
<%@ include file="footer.jsp" %>