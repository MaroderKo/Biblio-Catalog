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
        url: "ajax/students/save",
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
        url: "ajax/schoolclasses/save",
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
    $.get("ajax/schoolclasses/names", function(data)  {
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