function render(d) {
    var c = document.getElementById("canvas")
    var ctx=c.getContext("2d")
    
    // clear
    ctx.fillStyle="#001020"
    ctx.fillRect(0,0,c.width,c.height)
    
    // avatar
    ctx.fillStyle="#0000FF"
    ctx.fillRect(posx, posy, 10, 10)
    
    // fps
    ctx.strokeStyle = "#FFFFFF";
    ctx.font = '14px san-serif';
    ctx.textBaseline = 'bottom';
    ctx.strokeText('FPS: ' + fps, 20, 40);
    
}