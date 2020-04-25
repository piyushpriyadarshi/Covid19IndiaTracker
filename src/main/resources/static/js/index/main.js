$(document).ready(function () {
    // $('.sidenav').sidenav();
    // $('.tabs').tabs();
    // document.querySelector('.tabs-content.carousel').style.height = window.innerHeight + "px";
    getAllData();
    let allStates=[];
    ajaxCall("api/getAllStates","GET",null).then(data => {
        allStates=data;
        console.log(allStates);
        generateAllSelectForState(allStates);
    });


    console.log("inside ")
    $('#state').on('change', function () {
        let selectedValue = $(this).val();
        window.canvasId=[];
        if (selectedValue === 'INDIA All States') {
            let selectedStateData=[];

            let url="api/getIndiaTotal";
            ajaxCall(url,"GET",null).then(data => {
                selectedStateData=data;

                generateDataForChartAndPlotGraph(selectedStateData);
                updateTotalData(selectedStateData);
            });

        }
        else{
            let selectedStateData=[];
            let index=getIndexOf(allStates,selectedValue);
            if(index!= -1){
                let url="api/getStateWiseData/"+index;
                ajaxCall(url,"GET",null).then(data => {
                    selectedStateData=data;
                    generateDataForChartAndPlotGraph(selectedStateData);
                    updateTotalData(selectedStateData);
                });
            }


        }
    });

    $('#subscribeButton').click(function () {
        let selectedState = $('#stateForSubscribe').val();
        let selectedEmail = $('#emailForSubscribe').val();
        var obj={};
        obj.email=selectedEmail;
        obj.state=selectedState;
        let message=null;
        ajaxCall("api/subscribe","POST",obj).then(data => {
            message=data.message;

            toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-right",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "10000",
                "hideDuration": "10000",
                "timeOut": "10000",
                "extendedTimeOut": "5000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };

            toastr["success"]("you will get a test mail from System , Please check your inbox and spam, please move it to inbox", 'Mail Subscription');
            toastr["success"](message, "Mail Subscription");
        });
        console.log(obj);
    });

    $('#predictionButton').on('click', function () {
        let selectedDate = $('#datesForPrediction').val();
        let selectedValue = $('#stateForPrediction').val();


        if (selectedValue === 'INDIA All States') {
            let selectedStateData=[];

            let url="api/getIndiaTotal";
            ajaxCall(url,"GET",null).then(data => {
                selectedStateData=data;

                generateDataForChartAndPlotGraph(selectedStateData);
                updateTotalData(selectedStateData);
                let predictedObject=predictTotalCasesML(selectedDate);
                updatepredictedData(predictedObject.predictedTotalCase,predictedObject.predictedCured,predictedObject.predictedDeath);
                console.log(predictedObject);
            });

        }
        else{
            let selectedStateData=[];
            let index=getIndexOf(allStates,selectedValue);
            if(index!= -1){
                let url="api/getStateWiseData/"+index;
                ajaxCall(url,"GET",null).then(data => {
                    selectedStateData=data;
                    generateDataForChartAndPlotGraph(selectedStateData);
                    updateTotalData(selectedStateData);
                    let predictedObject=predictTotalCasesML(selectedDate);
                    updatepredictedData(predictedObject.predictedTotalCase,predictedObject.predictedCured,predictedObject.predictedDeath);
                    console.log(predictedObject);
                });
            }


        }

    });

    function updatepredictedData(totalCase,curedCase,totalDeath){
        $('#totalDeathDataPredicted').text(parseInt(totalDeath));
        $('#totalCasesDataPredicted').text(parseInt(totalCase));
        $('#totalCuredDataPredicted').text(parseInt(curedCase));
    }


});