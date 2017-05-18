/**
 * Created by cj on 18.05.17.
 */
$(document).ready(function(){

    $(".add-to-basket").click(function(){
        var id = $(this).data("id");

        $.post("/basket/add",
        {
            id: id
        },
        function(data){
            $("#basket-price").html(data);
        });
    });

    $(".delete-from-basket").click(function(){
        // $(this).parents('.caption').remove();
        var item = $(this).parents('.item');
        var id = $(this).data("id");
        $.post("/basket/remove",
            {
                id: id,
                quantity: 1
            },
            function(data, status){
                var json = JSON.parse(data);
                var quantity = json['quantity'];
                if(quantity === 0) {
                    item.remove();
                } else {
                    item.find('.itemPrice').html('Total price: ' + json['itemPrice']);
                    item.find('.quantity').html('Quantity: ' + quantity);
                }
                $('#basket-price').html(json['totalPrice']);
            });

    });
});