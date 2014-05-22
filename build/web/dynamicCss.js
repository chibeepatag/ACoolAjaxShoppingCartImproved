/**
 * Calls the tableRowColor function on load*/
$(document).ready(function(){    
    tableRowColor();
});

/*
 * Paints the table rows in alternating colors.
 **/
function tableRowColor(){
    $("table").find("tr").addClass(function(index) {
    var classResult = index%2==0?"oddRowBlue":"evenRowBlue";
    return classResult;
});
}