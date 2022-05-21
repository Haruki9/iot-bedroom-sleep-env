var temperatureData=[];
var humidityData=[];
var timeScale=[];
var tempDom=document.getElementById('temp-humidity');
var tempCharts=echarts.init(tempDom);
var tempOption;

let today=new Date().toLocaleDateString();

tempOption={
    tooltip:{
        trigger: 'axis',
            axisPointer:{
                type: 'cross',
                label:{
                    backgroundColor:'#283b56'
                }
            },
    },
    toolbox: {
        show: true,
        feature: {
            dataView: { readOnly: false },
        }
    },
    dataZoom: {
        show: false,
        start: 0,
        end: 100,
    },
    xAxis: {
        splitLine: {　　　　 show: false　　 },
        type: 'category',
        data: timeScale,
        axisPointer: {
            type: 'shadow'
        },
        // 改变x轴颜色
        axisLine: {
            lineStyle: {
                color: '#00a2e2',
                width: 1, // 这里是为了突出显示加上的
            }
        },
        axisTick: {
            show: true,
            interval: 0
        },
    },
    yAxis: [
        {
            type: 'value',
            scale: true,
            name: "temperature",
            max: 50,
            min: 0,
        },
        {
            type: 'value',
            scale: true,
            name: "humidity",
            max: 80,
            min: 0,
        },
    ],
    series:[
        {
            name: "temperature",
            type: 'line',
            lineStyle:{
                color: "#FFBF00"
            },
            smooth: true,
            yAxisIndex: 0,
            xAxisIndex: 0,
            data: temperatureData,
        },
        {
            name: "humidity",
            type: 'bar',
            itemStyle: {
                color: "#37A2FF"
            },
            yAxisIndex: 1,
            xAxisIndex: 0,
            data: humidityData
        }
    ]
};



function getTemperatureDate(){
    $.ajax({
        url: "/temperature",
        type: "GET",
        param: {
            beginDate : today
        },
        success: function (temperatureList){
            timeScale.splice(0,timeScale.length)
            temperatureData.splice(0,temperatureData.length)
            temperatureList.forEach((item)=>{
                temperatureData.push(item.data);
                timeScale.push(item.eventTime);
            })
            tempOption.xAxis.data=timeScale;
            tempOption.series[0].data=temperatureData;
            tempCharts.setOption(tempOption);

        },
        error:function (error){
            console.log('Humidity Error: ${error}')
        }
    });
}

function getHumidity(){
    $.ajax({
        url: "/humidity",
        type: "GET",
        param: {
            beginDate: today,
        },
        success: function (humidityList){
            humidityData.splice(0,humidityData.length);
            humidityList.forEach((item)=>{
                humidityData.push(item.data);
            })
            tempOption.series[1].data=humidityData;
            tempCharts.setOption(tempOption);

        },
        error:function (error){
            console.log('Humidity Error: ${error}')
        }
    });
}
getTemperatureDate();
getHumidity();

setInterval(function (){
    getTemperatureDate();
    getHumidity();
},1000*60)
