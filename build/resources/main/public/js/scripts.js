$(document).ready(function(){
  $(".flashcard").each(function(){
    var h1 = $(this).find("h1");
    var p = $(this).find('p');
    h1.click(function(){
      p.toggleClass('show');
    });
  });
});
