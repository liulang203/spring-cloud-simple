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
    <form method="post" action="/account/id/${account.id}/save">
        <form>
            <div class="form-group">
                <label for="exampleInputEmail1">Name</label>
                <input type="text" class="form-control" id="name" name="name" aria-describedby="emailHelp" placeholder="Enter email" value="${account.name}">
                <small id="emailHelp" class="form-text text-muted">Your name.</small>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Sex</label>
                <input type="text" class="form-control" id="sex" placeholder="Sex" name="sex" value="${account.sex}">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </form>
</div>
</body>
</html>