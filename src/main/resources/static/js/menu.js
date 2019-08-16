$(document).ready(function () {
    var host = location.href.split('/')
    $("#" + host[host.length -1]).attr("class", $("#" + host[host.length - 1]).attr("class") + " active")
})