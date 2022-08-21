package com.gkhy.cmsservice.controller;

import com.gkhy.servicebase.result.Result;
import com.gkhy.cmsservice.entity.CrmBanner;
import com.gkhy.cmsservice.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Background banner management interface
 * </p>
 *
 * @author leo
 * @since 2020-07-20
 */
@RestController
@RequestMapping("/educms/banneradmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> eduTeacherList = bannerService.findAll(page, limit);

        long total = eduTeacherList.getNumberOfElements ();

        return Result.success().data("total",total).data("rows",eduTeacherList.getContent());
    }

    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return Result.success();
    }

    @ApiOperation(value = "get Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return Result.success().data("item", banner);
    }

    @ApiOperation(value = "edit Banner")
    @PutMapping("update")
    public Result update(@RequestBody CrmBanner banner) {
        bannerService.update(banner);
        return Result.success();
    }

    @ApiOperation(value = "delete Banner")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.success();
    }
}

