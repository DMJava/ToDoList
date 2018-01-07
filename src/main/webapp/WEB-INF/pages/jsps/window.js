$("#btn-add").click(function() {
    $("#myForm").attr("action", "/addTask");
    $("#myForm").submit();
});

$("#btn-update").click(function() {
    $("#myForm").attr("action", "/updateTask");
    $("#myForm").submit();
});