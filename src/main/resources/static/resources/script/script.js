(function(){

    // getAuthenticationToken();

    clickDeleteButton();

    clickAddBalance();

    clickInfoButton();

    clickDetailButton();

    clickSubmitBalance();
}());

function clickAddBalance(){
    $('.balance-button').click(function(event){
        event.preventDefault();
        $('.modal').addClass('pops');
        clickCancelButton();
    });
}

function clickDeleteButton(){
    $('.delete-button').each(function(){
        let dataName = $(this).attr('data-name');
        let url = $(this).attr('href');

        $(this).click(function(event){
            event.preventDefault();
            $('.name').text(dataName.trim());
            $('.yes').attr('href', url);
            $('.modal').addClass('pops');
            clickCancelButton();
        });
    });
}

function clickCancelButton(){
    $('.cancel').click(function(event){
        event.preventDefault();
        $('.modal').attr('class', 'modal');
    });
}


function getAuthenticationToken(){
    let endPoint = `http://localhost:8080/api/account`;
    let payload = {
        "username": "ferdy",
        "password": "indocyber",
        "subject": "JWT for trollmarket",
        "audience": "MVC",
        "secretKey": "friday is a good day to die, are you willing to die this friday?"
    };
    $.ajax({
        type: "POST",
        url: endPoint,
        data: JSON.stringify(payload),
        contentType: "application/json",
        success: function (response){
            saveAuthenticationToken(response);
        }
    });
}

function saveAuthenticationToken({token, username}){
    sessionStorage.setItem("token", token);
    sessionStorage.setItem("username", username);
}

function getBearerToken(){
    let token = sessionStorage.getItem("token");
    let bearerToken = {
        "Authorization" : `Bearer ${token}`
    }
    return bearerToken;
}


function clickInfoButton(){
    $('.info-button').each(function(){
        let dataId = $(this).attr('data-id');

        $(this).click(function(event){
            event.preventDefault();
            $('.modal').addClass('pops');
            getProductInfo(dataId);
            clickCancelButton();
        });
    });
}

function getProductInfo(id){
    let endPoint = `http://localhost:8080/api/merch/${id}/info`;

    $.ajax({
        type: "GET",
        url: endPoint,
        crossDomain: false,
        // headers: getBearerToken(),
        success: function (response){
            renderInfoMerch(response);
        }
    });
}

function renderInfoMerch(merch){
    $('.modal #name').text(merch.name);
    $('.modal #category').text(merch.category);
    $('.modal #description').text(merch.description);
    $('.modal #price').text(merch.price);
    $('.modal #discontinue').text(merch.discontinue);
}


function clickDetailButton(){
    $('.detail-button').each(function(){
        let dataId = $(this).attr('data-id');

        $(this).click(function(event){
            event.preventDefault();
            $('.modal').addClass('pops');
            getProductDetail(dataId);
            clickCancelButton();
        });
    });
}

function getProductDetail(id){
    let endPoint = `http://localhost:8080/api/shop/${id}/info`;

    $.ajax({
        type: "GET",
        url: endPoint,
        crossDomain: false,
        // headers: getBearerToken(),
        success: function (response){
            renderShopDetail(response);
        }
    });
}

function renderShopDetail(product){
    $('.modal #name').text(product.name);
    $('.modal #category').text(product.category);
    $('.modal #description').text(product.description);
    $('.modal #price').text(product.price);
    $('.modal #seller').text(product.seller);
}

function clickSubmitBalance(){
    $(".submit-balance").click(function (event) {
        event.preventDefault();
        $(".validation-error").text("");
        let username = $("form #username").val();
        let balanceAddition = $("form #balanceAddition").val();
        addBalance({"name": name, "balanceAddition": balanceAddition});
    });
}
function addBalance(dto){
    let endPoint = `http://localhost:8080/api/profile/add-balance`;

    $.ajax({
        type: "POST",
        url: endPoint,
        crossDomain: false,
        // headers: getBearerToken(),
        data: JSON.stringify(dto),
        contentType: "application/json",
        success: function (response){
            location.reload();
        },
        error: function(response){
            let statusCode = response.status;
            if (response.statusCode == 422){
                let validationMessages = response.responseJSON;
                console.log(validationMessages);
                renderErrorValidation(validationMessages);
            }
        }
    });
}

function renderErrorValidation(data){
    $('.validation-error').text(data);
}