var airDom=document.getElementById('air-quality');
var airCharts=echarts.init(airDom);
var airQualityData=[]
var airOption;

var schema = [
    { name: 'tine', index: 0, text: '时间' },
    { name: 'CO', index: 1, text: 'CO' },
    { name: 'PM25', index: 2, text: 'PM2.5' },
    { name: 'PM10', index: 3, text: 'PM10' },
    { name: 'O2', index: 4, text: 'O2' },
    { name: 'CO2', index: 5, text: 'CO2' },
    { name: '质量', index: 6, text: '等级' }
];
var lineStyle = {
    width: 1,
    opacity: 0.5
};
airOption = {
    // legend: {
    //     bottom: 30,
    //     data: ['Beijing', 'Shanghai', 'Guangzhou'],
    //     itemGap: 20,
    //     textStyle: {
    //         color: '#fff',
    //         fontSize: 14
    //     }
    // },
    tooltip: {
        padding: 10,
        borderWidth: 1
    },
    parallelAxis: [
        {
            dim: 0,
            name: schema[0].text,
            inverse: true,
            type: 'category',
            nameLocation: 'start'
        },
        { dim: 1, name: schema[1].text },
        { dim: 2, name: schema[2].text },
        { dim: 3, name: schema[3].text },
        { dim: 4, name: schema[4].text },
        { dim: 5, name: schema[5].text },
        {
            dim: 6,
            name: schema[6].text,
            type: 'category',
            data: ['优', '良', '中', '劣', '差']
        }
    ],
    visualMap: {
        show: true,
        min: 0,
        max: 100,
        dimension: 2,
        top: "45%",
        inRange: {
            color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
            // colorAlpha: [0, 1]
        }
    },
    parallel: {
        left: '8%',
        right: '10%',
        bottom: 100,
        parallelAxisDefault: {
            type: 'value',
            name: '空气质量指标',
            nameLocation: 'end',
            nameGap: 10,
            nameTextStyle: {
                color: '#fff',
                fontSize: 12
            },
            axisLine: {
                lineStyle: {
                    color: '#aaa'
                }
            },
            axisTick: {
                lineStyle: {
                    color: '#777'
                }
            },
            splitLine: {
                show: true
            },
            axisLabel: {
                color: '#fff'
            }
        }
    },
    series: [
        {
            name: 'AQIndex',
            type: 'parallel',
            lineStyle: lineStyle,
            data: airQualityData
        },
    ]
};

function getAirQualityData(){
    $.ajax({
        url: "/bedroom/air-info",
        type: "GET",
        data: {
            property: "air_quality",
            date : today
        },
        success: function (airList){
            airQualityData.splice(0,airQualityData.length);
            airList.forEach((item)=>{
                airQualityData.push([item.eventTime, item.co, item.pm25, item.pm10, item.o2, item.co2,item.level]);
            })
            airOption.series[0].data=airQualityData;
            airCharts.setOption(airOption);
        },
        error:function (error){
            console.log('AirQuality data Error: ${error}')
        }
    });
}

setInterval(function (){
    getAirQualityData();
},1000*60)

getAirQualityData();