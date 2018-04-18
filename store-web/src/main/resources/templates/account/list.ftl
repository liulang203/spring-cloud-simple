<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
<#include "../common/load_resources.ftl">
</head>
<body>
<div class="container">
<#include "../common/nav.ftl" />
</div>
<div class="container" style="margin-top: 20px;">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Sex</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <#list accounts as acc>
        <tr>
            <th scope="row">${acc.id}</th>
            <td>${acc.name}</td>
            <td>${acc.sex}</td>
            <td><div class="btn-group" role="group" aria-label="Basic example">
                <a href="/account/name/${acc.name}/edit"><button type="button" class="btn btn-primary">Edit</button></a>
                <a href="/account/id/${acc.id}/delete"><button type="button" class="btn btn-danger">Delete</button></a>
            </div></td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>