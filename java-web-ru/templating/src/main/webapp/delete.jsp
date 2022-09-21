<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<html>
    <body>
        <form action="delete" method="post">
               Deleting user : ${user.get("id")} ${user.get("firstName")} ${user.get("lastName")}
              <input type="hidden" name="id" value=${user.get("id")}>
              <br> Press button to confirm : <button type="submit" class="btn btn-danger">Delete</bu
        </form>
    </body>
</html>
<!-- END -->
