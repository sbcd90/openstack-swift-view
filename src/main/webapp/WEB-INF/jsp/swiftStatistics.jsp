<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" type="text/css" href="css/main.css"/>
<link rel="stylesheet" type="text/css" href="css/vendor.css"/>
<div class="col-sm-3">
    <div class="left-navbar ng-scope" xmlns="http://www.w3.org/1999/html">
        <div class="panel panel-default">
            <div class="panel-heading">
                Objects
            </div>
            <div class="panel-body">
                <ul class="nav nav-pills nav-stacked">
                    <li>
                        <a class="userslist-link ng-isolate-scope">
                            <span class="ng-scope">Account</span>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="panel-body">
                <ul class="nav nav-pills nav-stacked">
                    <li>
                        <a class="userslist-link ng-isolate-scope">
                            <span class="ng-scope">Container</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="col-sm-9">
    <div class="panel panel-default mainpage ng-scope">
        <div class="panel-body">
            <div class="clearfix ng-scope">
                <ol class="breadcrumb pull-left">
                    <li>
                        Swift
                    </li>
                    <li class="active ng-binding">
                        Account
                    </li>
                </ol>
                <div class="pull-right top-margin-4">

                </div>
            </div>
            <hr class="ng-scope" />
            <div class="panel panel-default ng-scope" style="height: 500px">
                <div class="panel-heading clearfix">
                    <h3 class="panel-title pull-left">Details</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal ng-pristine ng-valid ng-valid-required" name="detailsForm" novalidate>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Bytes Used</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Bytes used" value="${bytesUsed}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Container Count</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Container Count" value="${containerCount}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Object Count</label>
                                <div class="col-sm-9">
                                   <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Object Count" value="${objectCount}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Temporary URL Key</label>
                                <div class="col-sm-9">
                                   <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Temporary URL Key" value="${temporaryUrlKey}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Metadata</label>
                                <div class="col-sm-9">
                                    <textarea disabled="disabled" rows="${countOfMetadata}" class="form-control instancename-input" placeholder="Display Metadata">${metadata}</textarea>
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Containers</label>
                                <div class="col-sm-9">
                                    <textarea disabled="disabled" rows="${countOfContainerNames}" class="form-control instancename-input" placeholder="Display Containers">${containerNames}</textarea>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
            <hr class="ng-scope" />
            <div class="clearfix ng-scope" style="${containerVisible}">
                <ol class="breadcrumb pull-left">
                    <li>
                        Container
                    </li>
                    <li class="active ng-binding">
                        ${container}
                    </li>
                </ol>
                <div class="pull-right top-margin-4">

                </div>
            </div>
            <hr class="ng-scope" />
            <div class="panel panel-default ng-scope" style="height: 300px">
                <div class="panel-heading clearfix">
                    <h3 class="panel-title pull-left">Details</h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal ng-pristine ng-valid ng-valid-required" name="detailsForm" novalidate>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Object Count</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Object Count" value="${containerObjectCount}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Container Total size</label>
                                <div class="col-sm-9">
                                    <input disabled="disabled" type="text" class="form-control instancename-input" placeholder="Display Container size" value="${containerTotalSize}" />
                                </div>
                            </div>
                        </fieldset>
                        <fieldset disabled="disabled">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">Metadata</label>
                                <div class="col-sm-9">
                                    <textarea disabled="disabled" rows="${containerCountOfMetadata}" class="form-control instancename-input" placeholder="Display Metadata">${containerMetadata}</textarea>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>