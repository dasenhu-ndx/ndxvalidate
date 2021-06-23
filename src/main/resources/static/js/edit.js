let csrfHeaderName = "X-CSRF-TOKEN";
let csrfValue = $("input[name='_csrf']").val();

function ajaxPost() {
    let formData = {
        labName: $("#LabName").val(),
        mode: $("#mode").val(),
        isRush: $("#IsRush").val(),
        docId: $("#doctorId").val(),
        customerId: $("#customerId").val(),
        add1: $("#address1").val(),
        add2: $("#address2").val(),
        city: $("#city").val(),
        state: $("#state").val(),
        zip: $("#zip").val(),
        fname: $("#fName").val(),
        lname: $("#lName").val(),
        email: $("#email").val(),
        phone: $("#phone").val(),
        licencsNo: $("#licenseNo").val(),
        npi: $("#npi").val(),
        notes: $("#labNote").val()


    };

    let requestId = $("#requestId").val();
    let url = contextpath + "api/request/update/" + requestId;
    console.log(formData);
    console.log(url);


    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        },
        data: JSON.stringify(formData),
        dataType: 'json',
        success: function (response) {
            window.location.assign("http://localhost:8080/dashboard");
            console.log(response);
        },
        error: function (e) {
            console.log("Error: ", e);
            console.log("data: ", JSON.stringify(formData))
            window.location.assign("http://localhost:8080/dashboard");
        }

    });
}

$(document).ready(function () {

    $("#updateRequest").submit(function (event) {
        event.preventDefault();
        ajaxPost();
    });


})