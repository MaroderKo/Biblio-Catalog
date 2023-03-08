function openAddClassMenu()
{
    debugger;
    $('#addClassButton').modal();
}

function studentSave()
{
    let form = $('#student-form');
    $.ajax({
        type: "POST",
        url: "students/save",
        data: form.serialize(),
        success: function ()
        {
            location.reload();
        }
    });
}
function classSave()
{
    let form = $('#class-form');
    $.ajax({
        type: "POST",
        url: "/class/save",
        data: form.serialize(),
        success: function ()
        {
            location.reload();
        }
    });
}
function classAutocomplete()
{
    const availableTags = getClassNames();
    $( "#student-class" ).autocomplete({
        source: availableTags
    });
}

function getClassNames()
{
    let names = [];
    $.get("/class/names", function(data)  {
        $.each(data,function (key,item) {
            names.add(item);
        })
    });
    return names;
}

function setClass(name)
{
    $('#student-class').val(name);
}