const contextpath = /*[[@{/}]]*/ "/";
let csrfHeaderName = "X-CSRF-TOKEN";
let csrfValue = "fetch";

function DisplayEDoc(data){
    console.log(data.state);
    var htmlData = "test";
    document.getElementById("EDoc").innerHTML = htmlData;
}


function EDoc(){
    let formDataED = {
        labName: $("#LabName").val(),
        docId: $("#doctorId").val()
    };
    console.log(formDataED.labName);
    let url = contextpath + "form/endpoints/"+formDataED.labName+"/"+formDataED.docId;
    console.log(url);
    $.getJSON(url,
        function (data) {
            console.log(data);
            DisplayEDoc(data);
        });
}


function DisplayECust(data){
console.log(data.hasOwnProperty(address2));

    document.getElementById("address1").value = data.address1;
    if (!data.hasOwnProperty(address2)) {
        document.getElementById("address2").value = '';
    } else {
        document.getElementById("address2").value = data.address2;
    }
    document.getElementById("city").value = data.city;
    document.getElementById("state").value = data.state;
    document.getElementById("zip").value = data.zipCode;
    document.getElementById("pName").value = data.practiceName;
    document.getElementById("phone").value = data.officePhone;
    if (!data.hasOwnProperty(dentalGroup)) {
        document.getElementById("dGroup").value = '';
    } else {
        document.getElementById("dGroup").value = data.dentalGroup;
    }

}


function ECust(){
    let formDataED = {
        labName: $("#LabName").val(),
        custId: $("#customerId").val()
    };
    console.log(formDataED.labName);
    let url = contextpath + "form/endpoints/"+formDataED.labName+"/"+formDataED.custId;
    console.log(url);
    $.getJSON(url,
        function (data) {
            console.log(data);
            DisplayECust(data);
        });
}

function ChangeDisplay(mode){
    console.log(mode);
    console.log($("#LabName").val());
    if(mode == 1){
        console.log("mode = 1");
        $('#doctorId').css('display','block');
        $('#doctorId').css('width', '100%');
        $('#doctorId1').css('display','block');
        $('#doctorId2').css('display','block');

        $('#fName').css('display','none');
        $('#fName1').css('display','none');
        $('#fName2').css('display','none');

        $('#lName').css('display','none');
        $('#lName1').css('display','none');
        $('#lName2').css('display','none');

        $('#customerId').css('display','none');
        $('#customerId').css('display','none');
        $('#customerId').css('display','none');
    } else if (mode == 2){
        console.log("mode = 2");

        $('#customerId').css('display','block');
        $('#customerId').css('width', '100%');
        $('#customerId1').css('display','block');
        $('#customerId2').css('display','block');

        // $('#address1').css('display','none');
        // $('#address11').css('display','none');
        // $('#address12').css('display','none');
        //
        // $('#address2').css('display','none');
        // $('#address21').css('display','none');
        // $('#address22').css('display','none');
        //
        // $('#city').css('display','none');
        // $('#city').css('display','none');
        // $('#city2').css('display','none');
        //
        // $('#state').css('display','none');
        // $('#state1').css('display','none');
        // $('#state2').css('display','none');
        //
        // $('#zip').css('display','none');
        // $('#zip1').css('display','none');
        // $('#zip2').css('display','none');

    } else {
        console.log("mode = 0");
        $('#doctorId').css('display','none');
        $('#customerId').css('display','none');
        $('#dhs').css('display','none');
        $('#doctorId1').css('display','none');
        $('#customerId1').css('display','none');
        $('#dhs1').css('display','none');
        $('#doctorId2').css('display','none');
        $('#customerId2').css('display','none');
        $('#dhs2').css('display','none');

    }

    if($("#LabName").val() == "ARHBR"){
        $('#dhs').css('display','block');
        $('#dhs').css('width', '100%');
        $('#dhs1').css('display','block');
        $('#dhs2').css('display','block');
    } else {
        $('#dhs').css('display','none');
        $('#dhs1').css('display','none');
        $('#dhs2').css('display','none');
    }

}


$(document).ready(function() {
    ChangeDisplay($("#mode").val());
    console.log($("#LabName").val());
    $('#mode').change(function(event) {
        event.preventDefault();
        console.log("made it to mode");
        ChangeDisplay($("#mode").val());
    });

    $('#LabName').change(function(event) {
        event.preventDefault();
        console.log("made it to mode");
        ChangeDisplay($("#mode").val());
    });

    $('#doctorId').change(function(event) {
        event.preventDefault();
        EDoc();
    });

    $('#customerId').change(function(event) {
        event.preventDefault();
        ECust();
    });


});