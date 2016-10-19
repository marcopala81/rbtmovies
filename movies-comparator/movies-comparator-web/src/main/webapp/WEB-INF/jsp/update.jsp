<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="header.jsp" %>
    <div class="outer centered">
        <div class="inner">
            <h2>Update results:</h2>
            <i>${moviesCompForm.message}</i>
            <br>
            <br>
            <c:if test="${not empty moviesCompForm.localMovies}">
	            <table>
	                <thead class="centered">
	                <tr><td class="centered dbheader" colspan="6">&gt;&gt;&gt; LOCAL DB MOVIES &lt;&lt;&lt;</tr>
	                <tr class="main">
	                    <th class="id">Id</th>
	                    <th class="id">OMBd ID</th>
	                    <th class="title">Title</th>
	                    <th class="rating">Rating</th>
	                    <th class="favourite">Favourite</th>
	                    <th class="favourite">Changed</th>
	                </tr>
	                </thead>
		            <c:forEach var="movieListIter" items="${moviesCompForm.localMovies}">
                        <tr>
                            <td class="centered courierBig">${movieListIter.id}</td>
                            <td class="centered courierBig">${movieListIter.imdbId}</td>
							<td>${movieListIter.title}</td>
							<td class="right courierBig">${movieListIter.rating}</td>
							<!-- Favourite -->
							<td class="centered favourite">
                                <c:if test="${movieListIter.favourite}"><img alt="- YES -" class="favourite" align="middle" src="/static/images/favourite.png" /></c:if>							
							</td>
							<td class="centered">
                                <c:if test="${movieListIter.changed}">- YES -</c:if>
                                <c:if test="${not movieListIter.changed}">NO</c:if>
							</td>
                         </tr>		                
		            </c:forEach>	                
	            </table>
	        </c:if>	        
	        <br>
	        <br>
	        <h3><a href="moviescomparator">Return to the comparison form...</a></h3>
        </div>
    </div>
<%@ include file="footer.jsp" %>