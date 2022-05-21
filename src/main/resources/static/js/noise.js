var noiseData=[];
var noiseDom=document.getElementById('noise');
var noiseCharts=echarts.init(noiseDom);
var noiseOption;


noiseOption={
    // Make gradient line here
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
            name: "Noise/ db",
        },
    ],
    series: [
        {
            type: 'line',
            lineStyle:{
                width: 3,
                color: 'rgba(159,159,159,0.5)'
            },
            areaStyle: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                    {
                        offset: 1,
                        color: 'rgb(187,255,154)'
                    },
                    {
                        offset: 0,
                        color: 'rgb(255,0,0)'
                    }
                ])
            },
            data: noiseData
        },
    ]
};
function getNoiseData(){
    $.ajax({
        url: "/noise",
        type: "GET",
        param: {
            beginDate : today
        },
        success: function (noiseList){
            noiseData.splice(0,noiseData.length);
            noiseList.forEach((item)=>{
                noiseData.push(item.data);
            })
            noiseOption.series[0].data=noiseData;
            noiseCharts.setOption(noiseOption);
        },
        error:function (error){
            console.log('Noise data Error: ${error}')
        }
    });
}
setInterval(function (){
    getNoiseData();
},1000*60)

getNoiseData();
