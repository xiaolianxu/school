$(function () {
    $('.icon-yonghu').mouseover(function () {
        if($('.userOpt').css('display')=='none'){
            $('.userOpt').show();
        }else {
            $('.userOpt').hide();
        }
    })
    $('body').on('mouseleave', '.userIcon', function () {
        $('.userOpt').hide();
    });
});
