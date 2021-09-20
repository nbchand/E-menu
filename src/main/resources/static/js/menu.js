//deal with button clicking
$('.btn').on('click', function (e) {
    e.preventDefault();
    if($(this).parent().hasClass('active')){
        $(this).parent().removeClass('active');
        $(this).html("SELECT ITEM")
        return;
    }
    $(this).parent().addClass('active');
    $(this).html("SELECTED")
  });