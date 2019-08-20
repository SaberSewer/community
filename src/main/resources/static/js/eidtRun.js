$(document).ready(function () {
    var E = window.wangEditor
    var editor = new E('#text-table')
    editor.customConfig.uploadImgServer = "/upload"
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
    editor.customConfig.uploadFileName = "file"
    editor.create()
})

