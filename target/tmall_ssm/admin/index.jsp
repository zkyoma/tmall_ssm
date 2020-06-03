<%
    request.getServletContext().setAttribute("contextPath", request.getContextPath());
    response.sendRedirect("../admin_category_list");
%>