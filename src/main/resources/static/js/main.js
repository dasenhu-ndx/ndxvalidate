const contextpath = /*[[@{/}]]*/ "/";
let csrfHeaderName = "X-CSRF-TOKEN";
let csrfValue = "fetch";
const States = ['AL','AK','AZ','AR','CA','CO','CT','DE','DC','FL','GA','HI','ID','IL','IN','IA','KS','KY','LA','ME','MD','MA','MI','MN','MS','MO','MT','NE','NV','NH','NJ','NM','NY','NC','ND','OH','OK','OR','PA','RI','SC','SD','TN','TX','UT','VT','VA','WA','WV','WI','WY'];
const StateWebsites = {
    AL: 'http://www.dentalboard.org/public/current-rosters/',
    AK: 'https://www.commerce.alaska.gov/cbp/main/Search/Professional',
    AZ: 'https://dentalboard.az.gov/directory',
    AR: 'https://search.statesolutions.us/?A=ASBDE&AID=RS&GUID=20C2E912D0C849AB9ADC2353CD5970EB',
    CA: 'https://search.dca.ca.gov/',
    CO: 'https://apps.colorado.gov/dora/licensing/Lookup/LicenseLookup.aspx',
    CT: 'https://www.elicense.ct.gov/Lookup/LicenseLookup.aspx',
    DE: 'https://delpros.delaware.gov/OH_HomePage',
    DC: 'https://doh.force.com/ver/s/',
    FL: 'https://mqa-internet.doh.state.fl.us/MQASearchServices/Home',
    GA: 'https://gadch.mylicense.com/verification/Search.aspx?facility=N',
    HI: 'https://mypvl.dcca.hawaii.gov/public-license-search/',
    ID: 'https://elitepublic.isbd.idaho.gov/IBODPublic/LPRBrowser.aspx',
    IL: 'https://online-dfpr.micropact.com/Lookup/LicenseLookup.aspx',
    IN: 'https://mylicense.in.gov/EVerification/Search.aspx',
    IA: 'https://eservices.iowa.gov/PublicPortal/Iowa/IDB/licenseQuery/LicenseQuery.jsp',
    KS: 'https://www.kansas.gov/dental-verification/index.do',
    KY: 'https://secure.kentucky.gov/formservices/KYBD/DentistLookup',
    LA: 'https://www.membersbase.com/lsbdweb/licenseverification.htm',
    ME: 'https://www.pfr.maine.gov/almsonline/almsquery/SearchIndividual.aspx',
    MD: 'https://mdbod.mylicense.com/Verification/Search.aspx',
    MA: 'https://checkalicense.hhs.state.ma.us/MyLicenseVerification/',
    MI: 'https://aca-prod.accela.com/MILARA/GeneralProperty/PropertyLookUp.aspx?isLicensee=Y&TabName=APO',
    MN: 'https://bodgl.hlb.state.mn.us/GLSuite_MNBOD_PROD/GLSuiteWeb/Clients/mnbod/Public/Licensee/LicenseeSearch.aspx',
    MS: 'https://www.dentalboard.ms.gov/licensee-search',
    MO: 'https://pr.mo.gov/licensee-search-division.asp',
    MT: 'https://ebizws.mt.gov/PUBLICPORTAL/searchform?mylist=licenses',
    NE: 'https://www.nebraska.gov/LISSearch/search.cgi',
    NV: 'https://dental.nv.gov/Consumers/License_Verification/',
    NH: 'https://www.app-support.nh.gov/licensing/',
    NJ: 'https://newjersey.mylicense.com/verification/Search.aspx',
    NM: 'http://verification.rld.state.nm.us/',
    NY: 'http://www.op.nysed.gov/opsearches.htm',
    NC: 'https://www.membersbase.com/NCBDESearch/license_verification.htm',
    ND: 'https://www.nddentalboard.org/verify/',
    OH: 'https://elicense.ohio.gov/OH_HomePage',
    OK: 'https://www.ok.gov/dentistry/License_Verification/index.html',
    OR: 'https://www.oregon.gov/dentistry/Pages/verify-license.aspx',
    PA: 'https://www.pals.pa.gov/#/page/search',
    RI: 'https://health.ri.gov/find/licensees/index.php?prof=Dental',
    SC: 'https://verify.llronline.com/LicLookup/LookupMain.aspx?AspxAutoDetectCookieSupport=1',
    SD: 'https://www.sdboardofdentistry.org/verify.asp',
    TN: 'https://apps.health.tn.gov/licensure/default.aspx',
    TX: 'http://ls.tsbde.texas.gov/index.php',
    UT: 'https://secure.utah.gov/llv/search/index.html',
    VT: 'https://secure.professionals.vermont.gov/prweb/app/default/ybVBleIGIHMlPa8qpM9BaiNEZfDPENuF*/!STANDARD',
    VA: 'https://dhp.virginiainteractive.org/Lookup/Index',
    WA: 'https://fortress.wa.gov/doh/providercredentialsearch/',
    WV: 'https://wvbodprod.glsuite.us/GLSuiteWeb/Clients/WVBOD/Public/Verification/Search.aspx',
    WI: 'https://licensesearch.wi.gov/',
    WY: 'https://dental.wyo.gov/public/lookup'
}
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
    let url = contextpath + "form/endpoints/ed/"+formDataED.labName+"/"+formDataED.docId;
    console.log(url);
    $.getJSON(url,
        function (data) {
            DisplayEDoc(data);
        });
}


function DisplayECust(data){


    document.getElementById("address1").value = data.address1;
    if (!data.hasOwnProperty('address2')) {
        document.getElementById("address2").value = '';
    } else {
        document.getElementById("address2").value = data.address2;
    }
    document.getElementById("city").value = data.city;
    document.getElementById("state").value = data.state;
    document.getElementById("zip").value = data.zipCode;
    document.getElementById("pName").value = data.practiceName;
    document.getElementById("phone").value = data.officePhone;
    if (!data.hasOwnProperty('dentalGroup')) {
        document.getElementById("dGroup").value = '';
    } else {
        document.getElementById("dGroup").value = data.dentalGroup;
    }

    if(data.warning == "no Customer") {
        ChangeDisplay(0);
    }

}


function ECust(){
    let correctLab = ""
    let customerID = "none"
    if ($("#LabName").val() == 'PAIRW-ADL' || $("#LabName").val() == 'PAIRW-IDA'){
        correctLab = "PAIRW";
    } else if ($("#LabName").val() == 'TXCON' || $("#LabName").val() == 'CAHAW' || $("#LabName").val() == 'CAHAO' || $("#LabName").val() == 'CAHER' || $("#LabName").val() == 'CAELS' || $("#LabName").val() == 'NVLAS' || $("#LabName").val() == 'NYWHI' ) {
        correctLab = "WCDL";
    } else {
        correctLab = $("#LabName").val();
    }

    if($("#customerId").val() !== "" && typeof $('#customerId').val() !== 'undefined'){
        customerID = $("#customerId").val();
    }
    let formDataED = {
        labName: correctLab,
        custId: customerID
    };
    let url = contextpath + "form/endpoints/ec/"+formDataED.labName+"/"+formDataED.custId;

    $.getJSON(url,
        function (data) {

            DisplayECust(data);
        });
}

function ChangeDisplay(mode){

    if(mode == 1){

        $('#doctorId').css('display','block');
        $('#doctorId').css('width', '100%');
        $('#doctorId1').css('display','block');
        $('#doctorId2').css('display','block');

        $('#customerId').css('display','none');
        $('#customerId').css('display','none');
        $('#customerId').css('display','none');
    } else if (mode == 2){


        $('#customerId').css('display','block');
        $('#customerId').css('width', '100%');
        $('#customerId1').css('display','block');
        $('#customerId2').css('display','block');

        $('#doctorId').css('display','none');
        $('#doctorId1').css('display','none');
        $('#doctorId2').css('display','none');


    } else {

        $('#doctorId').css('display','none');
        $('#customerId').css('display','none');

        $('#doctorId1').css('display','none');
        $('#customerId1').css('display','none');

        $('#doctorId2').css('display','none');
        $('#customerId2').css('display','none');


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

function ValidateLicenseState(){
    let num = $('#licenceNo').val();
    let good = false;
    let currentState;
    for (let i = 0; i < States.length; i++) {
        let end = "/"+States[i];
        if (num.endsWith(end)){
            good = true;

            currentState = States[i];
        } else {
            console.log("bad does not end with", end);
        }
    }

    if (!good){
        document.getElementById("licenceNo").value = "";
        document.getElementById("licenseNoError").style.color = "red";
        document.getElementById("licenseNoError").innerHTML = "The license must be followed by a '/' and then the state abbreviation";
        document.getElementById("goodLicense").innerHTML = "&#10060"
    } else {
        document.getElementById("licenseNoError").innerHTML = "";
        document.getElementById("goodLicense").innerHTML = "&#9989"
        document.getElementById("licenseNoError").style.color = "black";
        let msgString = "You can verify the license  <a href=" + StateWebsites[currentState] +" target=\"_blank\">here</a>"
        document.getElementById("licenseNoError").innerHTML = msgString;
    }
}

function NPICheck() {
    let url = "https://npiregistry.cms.hhs.gov/api/?number=";
    // Search by Name
    // https://npiregistry.cms.hhs.gov/api/?number=&enumeration_type=NPI-1&taxonomy_description=Dentist&first_name=Nathan&use_first_name_alias=&last_name=ABRAMSON&organization_name=&address_purpose=&city=&state=&postal_code=&country_code=&limit=&skip=&version=2.1

    if($('#nip').val() != "" && typeof $('#nip').val() !== 'undefined' ){

        url = url + $('#nip').val() + "&enumeration_type=NPI-1&taxonomy_description=Dentist&first_name=";
    } else {
        url = url +"&enumeration_type=NPI-1&taxonomy_description=Dentist&first_name=";
    }
    if($('#fName').val() != "" && typeof $('#fName').val() !== 'undefined' ){

        url = url + $('#fName').val() + "&use_first_name_alias=&last_name="+$('#lName').val()+"&organization_name=&address_purpose=&city=&state=&postal_code=&country_code=&limit=&skip=&version=2.1";
    } else {
        url = url + "&use_first_name_alias=&last_name="+$('#lName').val()+"&organization_name=&address_purpose=&city=&state=&postal_code=&country_code=&limit=&skip=&version=2.1&callback=?";
    }


    $.getJSON(url,
        function (data) {

            ProcessNPI(data);
        });

    // $.ajax({
    //     type: 'GET',
    //     url: url,
    //     beforeSend: function(xhr){
    //         xhr.setRequestHeader(csrfHeaderName, csrfValue);
    //     },
    //     success: function(data) {
    //         console.log(data);
    //     },
    //     error: function(error) {
    //         console.log("FAIL....=================");
    //     }
    // });
}
//
// function NPICheckByNPI(){
//
//     // search by NPI
//     // https://npiregistry.cms.hhs.gov/api/?number=1457505869&enumeration_type=NPI-1&taxonomy_description=Dentist&first_name=&use_first_name_alias=&last_name=&organization_name=&address_purpose=&city=&state=&postal_code=&country_code=&limit=&skip=&version=2.1
// }

function ProcessNPI(info){
    console.log("made it this far");
    console.log(info);
}


$(document).ready(function() {
    ChangeDisplay($("#mode").val());

    $('#mode').change(function(event) {
        event.preventDefault();

        ChangeDisplay($("#mode").val());
    });

    $('#LabName').change(function(event) {
        event.preventDefault();

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

    $('#licenceNo').change(function(event) {
        event.preventDefault();
        ValidateLicenseState();
    });

    $('#nip').change(function(event) {
        event.preventDefault();
        NPICheck();
    });

    $('#lName').change(function(event) {
        event.preventDefault();
        NPICheck();
    });

    $('#fName').change(function(event) {
        event.preventDefault();
        NPICheck();
    });


});