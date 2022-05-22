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
            type: "category",
            data: timeScale,
            axisLine: {
                lineStyle: {
                    color: '#00a2e2',
                    width: 1, // 这里是为了突出显示加上的
                }
            },
        },
    ],
    yAxis: [
        {
            type: 'value',
            scale: true,
            name: "Noise/ db",
            axisLine: {
                lineStyle: {
                    color: '#00a2e2',
                    width: 1, // 这里是为了突出显示加上的
                }
            },
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
        url: "/bedroom/basic-info",
        type: "GET",
        data: {
            property: "noise",
            date : today
        },
        success: function (noiseList){
            noiseData.splice(0,noiseData.length);
            noiseList.forEach((item)=>{
                noiseData.push(item.data);
            })
            noiseOption.xAxis.data=timeScale;
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
