<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	
<script>
$(function(){
	$("ul.pagination li.disabled a").click(function(){
		return false;
	});
});

</script>


<nav>
  <ul class="pagination">
    <li <c:if test="${!pageInfo.hasPreviousPage}">class="disabled"</c:if>>
      <a  href="?pn=1${params}" aria-label="Previous" >
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    <li <c:if test="${!pageInfo.hasPreviousPage}">class="disabled"</c:if>>
      <a  href="?pn=${pageInfo.pageNum - 1}${params}" aria-label="Previous" >
        <span aria-hidden="true">&lsaquo;</span>
      </a>
    </li>    

    <c:forEach begin="1" end="${pageInfo.pages}" varStatus="status">

		    <li <c:if test="${status.count == pageInfo.pageNum}">class="disabled"</c:if>>
		    	<a  
		    	href="?pn=${status.count}${params}"
		    	<c:if test="${status.count == pageInfo.pageNum}">class="current"</c:if>
		    	>${status.count}</a>
		    </li>
		
    </c:forEach>

    <li <c:if test="${!pageInfo.hasNextPage}">class="disabled"</c:if>>
      <a href="?pn=${pageInfo.pageNum + 1}${params}" aria-label="Next">
        <span aria-hidden="true">&rsaquo;</span>
      </a>
    </li>
    <li <c:if test="${!pageInfo.hasNextPage}">class="disabled"</c:if>>
      <a href="?pn=${pageInfo.pages}${params}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
