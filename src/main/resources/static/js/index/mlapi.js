

function getDates(stopDate) {
        var dateArray = [];
        var currentDate = moment(startDate);
        var stopDate = moment(stopDate);
        while (currentDate <= stopDate) {
            dateArray.push( moment(currentDate).format('YYYY-MM-DD') )
            currentDate = moment(currentDate).add(1, 'days');
        }
        return dateArray;
}


function buildMlMODEL(x,y,p,q) {
    window.startDate=new Date(2020,3,1);
    let x1 = [];
    for (let i = 0; i < x.length; i++) {
        x1.push(parseInt(i + 1));
    }
    const expRegression1 = new ML.ExponentialRegression(x1, y);
    const simpleRegression1 = new ML.SimpleLinearRegression(x1, y);
    const degree = 3;
    const polynomialRegression1 = new ML.PolynomialRegression(x1, y, degree);
    const powerRegression1 = new ML.PowerRegression(x1, y);

    let totalCasesModel=new Object();
    totalCasesModel.expRegression=expRegression1;
    totalCasesModel.simpleRegression=simpleRegression1;
    totalCasesModel.polynomialRegression=polynomialRegression1;
    totalCasesModel.powerRegression=powerRegression1;


    //total Cured ML MODEL

    const expRegression2 = new ML.ExponentialRegression(x1, p);
    const simpleRegression2 = new ML.SimpleLinearRegression(x1, p);

    const polynomialRegression2 = new ML.PolynomialRegression(x1, p, degree);
    const powerRegression2 = new ML.PowerRegression(x1, p);

    let totalCuredModel=new Object();
    totalCuredModel.expRegression=expRegression2;
    totalCuredModel.simpleRegression=simpleRegression2;
    totalCuredModel.polynomialRegression=polynomialRegression2;
    totalCuredModel.powerRegression=powerRegression2;


    //total Death ML MODEL

    const expRegression3 = new ML.ExponentialRegression(x1, q);
    const simpleRegression3 = new ML.SimpleLinearRegression(x1, q);

    const polynomialRegression3 = new ML.PolynomialRegression(x1, q, degree);
    const powerRegression3 = new ML.PowerRegression(x1, q);

    let totalDeathModel=new Object();
    totalDeathModel.expRegression=expRegression3;
    totalDeathModel.simpleRegression=simpleRegression3;
    totalDeathModel.polynomialRegression=polynomialRegression3;
    totalDeathModel.powerRegression=powerRegression3;





    window.MlModel={};
    window.MlModel.totalCasesModel=totalCasesModel;
    window.MlModel.totalCuredModel=totalCuredModel;
    window.MlModel.totalDeathModel=totalDeathModel;


    console.log(window.MlModel);
}

function predictindiaTotal(dateArray) {
    predictedTotalArray=[];
    predictedCuredArray=[];
    predictedDeathArray=[];
    let startDate = moment(window.startDate, "DD.MM.YYYY");
    for (let i = 0; i < dateArray.length; i++) {
        let endDate = moment(dateArray[i]);
        let diff=endDate.diff(startDate, 'days')+1;


        let predictedScore=(window.MlModel.totalCasesModel.expRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.powerRegression.predict(diff));
        let predictedScore1=(window.MlModel.totalCuredModel.expRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.powerRegression.predict(diff));
        let predictedScore2=(window.MlModel.totalDeathModel.expRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.powerRegression.predict(diff));
        predictedTotalArray.push(predictedScore/4);
        predictedCuredArray.push(predictedScore1/4);
        predictedDeathArray.push(predictedScore2/4);
    }
    let obj={};
    obj.predictedTotalArray=predictedTotalArray;
    obj.predictedCuredArray=predictedCuredArray;
    obj.predictedDeathArray=predictedDeathArray;

    return obj;

}


function predictTotalCasesML(date) {
    let startDate = moment(window.startDate);
    let endDate = moment(date);
    let diff=endDate.diff(startDate, 'days')+1;

    console.log(diff);
    let predictedScore=(window.MlModel.totalCasesModel.expRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalCasesModel.powerRegression.predict(diff));
    let predictedScore1=(window.MlModel.totalCuredModel.expRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalCuredModel.powerRegression.predict(diff));
    let predictedScore2=(window.MlModel.totalDeathModel.expRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.simpleRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.polynomialRegression.predict(parseInt(diff))+window.MlModel.totalDeathModel.powerRegression.predict(diff));
    let obj={};
    obj.predictedTotalCase=predictedScore/4;
    obj.predictedCured=predictedScore1/4;
    obj.predictedDeath=predictedScore2/4;
    return obj;
}