var lightData=[];
var lightDom=document.getElementById('light');
var lightCharts=echarts.init(lightDom);
var lightOption;

lightOption={
    // Make gradient line here
    visualMap: [
        {
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 0,
            max: 50
        }
    ],
    tooltip: {
        trigger: 'axis',
        axisPointer:{
            type: 'cross',
            label:{
                backgroundColor:'#283b56'
            }
        },
    },
    xAxis: [
        {
            data: timeScale
        },
    ],
    yAxis: [
        {
            type: 'value',
            scale: true,
            name: "light/ lux",
        },
    ],
    series: [
        {
            type: 'line',
            lineStyle:{
                width: 3,
            },
            showSymbol: false,
            data: lightData
        },
    ]
};

function getLightData(){
    $.ajax({
        url: "/light",
        type: "GET",
        param: {
            beginDate : today
        },
        success: function (lightList){
            lightData.splice(0,lightData.length);
            lightList.forEach((item)=>{
                lightData.push(item.data);
            })
            lightOption.series[0].data=lightData;
            lightCharts.setOption(lightOption);
        },
        error:function (error){
            console.log('Light data Error: ${error}')
        }
    });
}

setInterval(function (){
    getLightData();
},1000*60)

getLightData();
