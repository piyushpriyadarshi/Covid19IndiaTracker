async function ajaxCall(url,method,data){
   return await $.ajax({
        // the server script you want to send your data to
        'url': url,
        // all of your POST/GET variables
        'data': data,
        // you may change this to GET, if you like...
        'type': method,
        // the kind of response that you want from the server
        //
   });
}

function generateAllSelectForState(allStates){
    $('select').empty().html(' ');

    $('select').append(
        $('<option></option>').attr('value', '-1').text('Select')
    );
    $('select').append(
        $('<option></option>')
            .attr('value', 'INDIA All States')
            .text('INDIA All States')
    );
    for (let i = 0; i < allStates.length; i++) {
        $('select').append(
            $('<option></option>').attr('value', allStates[i]).text(allStates[i])
        );
    }
    $('select').closest('.input-field').children('span.caret').remove();

}

function getIndexOf(str,pattern){
    return str.indexOf(pattern);
}

function poulateChart(canvasId,label,chartdata,chartType,color,typeData){
    var parent = document.getElementById(canvasId+'Container');
    var child = document.getElementById(canvasId);
    parent.removeChild(child);
    let canvasElement='<canvas id="'+canvasId+'" width="600" height="700" ></canvas>';
    parent.innerHTML =canvasElement ;
    console.log(canvasElement);

    // for (let i = 0; i < window.canvasId.length; i++) {
    //     if(window.canvasId[i] !=null){
    //         let id=window.canvasId[i].canvas.id;
    //         if(id===canvasId){
    //             window.canvasId[i].destroy();
    //             console.log(window.canvasId[i]+" Destroyed");
    //             window.canvasId.splice(i, 1);
    //         }
    //     }
    //
    // }

    let option = {
        layout: {
            padding: 10,
        },
        legend: {
            position: 'bottom',
        },
        title: {
            display: true,
            text: typeData,
        },

    };
    let chart=new Chart(document.getElementById(canvasId).getContext('2d'), {
        type: chartType, // also try bar or other graph types

        // The data for our dataset
        data: {
            labels: label,
            // Information about the dataset
            datasets: [
                {
                    label: typeData,
                    backgroundColor: color,
                    borderColor: 'black',
                    data: chartdata,
                },
            ],
        },

        // Configuration options
        options: option,
    });
}


function generateChart(label,totalCasesData,totalCuredData,totalDeathData){
    if(window.totalCuredChart!=null){
        totalCuredChart.destroy();
        console.log("totalCuredChart destroyed");
    }
    if(window.totalCasesChart!=null){
        totalCasesChart.destroy();
        console.log("totalCasesChart destroyed");
    }
    if(window.totalDeathChart!=null){
        totalDeathChart.destroy();
        console.log("totalCuredChart destroyed");
    }
    let options = {
        layout: {
            padding: 10,
        },
        legend: {
            position: 'bottom',
        },
        title: {
            display: true,
            text: 'Total Cases',
        },
        scales: {
            yAxes: [
                {
                    scaleLabel: {
                        display: true,
                        labelString: 'No of Case',
                    },
                },
            ],
            xAxes: [
                {
                    scaleLabel: {
                        display: true,
                        labelString: 'Date',
                    },
                },
            ],
        },
    };
    let optionsTotalCured = {
        layout: {
            padding: 10,
        },
        legend: {
            position: 'bottom',
        },
        title: {
            display: true,
            text: 'Total Cured',
        },
        scales: {
            yAxes: [
                {
                    scaleLabel: {
                        display: true,
                        labelString: 'No of Cured',
                    },
                },
            ],
            xAxes: [
                {
                    scaleLabel: {
                        display: true,
                        labelString: 'Date',
                    },
                },
            ],
        },
    };

    var ctx = document.getElementById('totalCured').getContext('2d');
    window.totalCuredChart = new Chart(ctx, {
        type: 'line', // also try bar or other graph types

        // The data for our dataset
        data: {
            labels: label,
            // Information about the dataset
            datasets: [
                {
                    label: 'Total Cured',
                    backgroundColor: 'rgb(108, 184, 73)',
                    borderColor: 'royalgreen',
                    data: totalCuredData,
                },
            ],
        },

        // Configuration options
        options: optionsTotalCured,
    });

     window.totalCasesChart = new Chart(
        document.getElementById('totalCases').getContext('2d'),
        {
            type: 'line', // also try bar or other graph types

            // The data for our dataset
            data: {
                labels: label,
                // Information about the dataset
                datasets: [
                    {
                        label: 'Total Cases',
                        backgroundColor: 'rgb(97, 79, 79)',
                        borderColor: 'black',
                        data: totalCasesData,
                    },
                ],
            },

            // Configuration options
            options: options,
        }
    );

    window.totalDeathChart = new Chart(
        document.getElementById('totalDeath').getContext('2d'),
        {
            type: 'line', // also try bar or other graph types

            // The data for our dataset
            data: {
                labels: label,
                // Information about the dataset
                datasets: [
                    {
                        label: 'Total Death',
                        backgroundColor: 'rgb(253, 31, 31)',
                        borderColor: 'black',
                        data: totalDeathData,
                    },
                ],
            },

            // Configuration options
            options: options,
        }
    );
}


function generateDataForChartAndPlotGraph(selectedStateData){
    if(selectedStateData.length>0){
        let label = [];
        let totalCasesData = [];
        let totalCuredData = [];
        let totalDeathData = [];
        for (let i = 0; i < selectedStateData.length; i++) {
            var date = new Date(selectedStateData[i].dataDate);
            var formattedDate = moment(date).format('DDMMMYYYY');
            label.push(formattedDate);
            totalCasesData.push(selectedStateData[i].totalCase);
            totalCuredData.push(selectedStateData[i].curedCase);
            totalDeathData.push(selectedStateData[i].totalDeath);
        }
        buildMlMODEL(label,totalCasesData,totalCuredData,totalDeathData);
        let datesArray=getDates(new Date(2020,4,3));
        let obj=predictindiaTotal(datesArray);

        generateChart(label,totalCasesData,totalCuredData,totalDeathData);
        poulateChart("advanceTotalCases",label,totalCasesData,'pie','rgb(97, 79, 79)',"Total Cases By Date");
        poulateChart("advanceTotalCured",label,totalCuredData,'pie','rgb(108, 184, 73)',"Total Cured By Date");
        poulateChart("advanceTotalDeath",label,totalDeathData,'pie','rgb(253, 31, 31)',"Total Death By Date");

        poulateChart("predictedTotalCases",datesArray,obj.predictedTotalArray,'line','rgb(97, 79, 79)',"Total Cases Predicted ");
        poulateChart("predictedTotalCured",datesArray,obj.predictedCuredArray,'line','rgb(108, 184, 73)',"Total Cured Predicted ");
        poulateChart("predictedTotalDeath",datesArray,obj.predictedDeathArray,'line','rgb(253, 31, 31)',"Total Death Predicted ");
    }
    else {
        console.log("Data is not correct");
    }

}

function updateTotalData(selectedStateData){
    let totalCasediff= (selectedStateData[selectedStateData.length-1].totalCase)-(selectedStateData[selectedStateData.length-2].totalCase);
    let totalCuredDiff= (selectedStateData[selectedStateData.length-1].curedCase)-(selectedStateData[selectedStateData.length-2].curedCase);
    let totalDeathDiff= (selectedStateData[selectedStateData.length-1].totalDeath)-(selectedStateData[selectedStateData.length-2].totalDeath);
    console.log(totalCasediff);
    $('#totalDeathData').text(
        selectedStateData[selectedStateData.length-1].totalDeath
    );
    $('#totalCasesData').text(
        selectedStateData[selectedStateData.length-1].totalCase
    );
    $('#totalCuredData').text(
        selectedStateData[selectedStateData.length-1].curedCase
    );
}