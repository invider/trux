function render(d) {
    var c = document.getElementById("canvas")
    var ctx=c.getContext("2d")
    
    // clear
    ctx.fillStyle="#001020"
    ctx.fillRect(0,0,c.width,c.height)
    
    // fps
    ctx.strokeStyle = "#FFFFFF";
    ctx.lineWidth = 1;
    ctx.font = '14px san-serif';
    ctx.textBaseline = 'bottom';
    ctx.strokeText('FPS: ' + fps, 20, 40);
    
    // status
    ctx.strokeStyle = "#FFFF00";
    ctx.lineWidth = 1;
    ctx.font = '14px san-serif';
    ctx.textBaseline = 'bottom';
    ctx.strokeText(state.status, 70, 40);
    
    // surface
    ctx.strokeStyle="#003080"
    ctx.lineWidth = 4;
    ctx.beginPath();
    ctx.moveTo(0, c.height - 50);
    ctx.lineTo(c.width, c.height - 50);
    ctx.stroke();
    
    
    // draw platform
    for (var i = 0; i < state.platforms.length; i++) {
       platform = state.platforms[i];
   
       // avatar
       if (platform.team == 1) {
           ctx.fillStyle="#0000FF"
       } else if (platform.team == 2) {
           ctx.fillStyle="#FF0000"
       } else if (platform.team == 3) {
           ctx.fillStyle="#00FF00"
       } else if (platform.team == 4) {
           ctx.fillStyle="#FFFF00"
       } else {
           ctx.fillStyle="#FFFFFF"
       }
       
       ctx.fillRect(platform.x - posx, c.height - 60 - platform.y, 10, 10)    
    }
    
}