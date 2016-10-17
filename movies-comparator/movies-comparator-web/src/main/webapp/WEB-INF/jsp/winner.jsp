<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@ include file="header.jsp" %>
    <div class="outer">
        <div class="inner">
        <h2>${mcForm.message}   <c:if test="${mcForm.winnerFound}">${mcForm.winnerTitle}!!!</c:if></h2>
        <br>
        <h3><a href="moviescomparator">New search...</a></h3>
        </div>
    </div>
<%@ include file="footer.jsp" %>