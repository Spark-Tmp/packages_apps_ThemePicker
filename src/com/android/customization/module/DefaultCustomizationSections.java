package com.android.customization.module;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.android.customization.model.color.ColorSectionController;
import com.android.customization.model.font.FontManager;
import com.android.customization.model.font.FontSectionController;
import com.android.customization.model.grid.GridOptionsManager;
import com.android.customization.model.grid.GridSectionController;
import com.android.customization.model.iconpack.IconPackManager;
import com.android.customization.model.iconpack.IconPackSectionController;
import com.android.customization.model.mode.DarkModeSectionController;
import com.android.customization.model.themedicon.ThemedIconPackSectionController;
import com.android.customization.model.theme.OverlayManagerCompat;
import com.android.customization.model.themedicon.ThemedIconSectionController;
import com.android.customization.model.themedicon.ThemedIconSwitchProvider;
import com.android.wallpaper.model.CustomizationSectionController;
import com.android.wallpaper.model.CustomizationSectionController.CustomizationSectionNavigationController;
import com.android.wallpaper.model.PermissionRequester;
import com.android.wallpaper.model.WallpaperColorsViewModel;
import com.android.wallpaper.model.WallpaperPreviewNavigator;
import com.android.wallpaper.model.WallpaperSectionController;
import com.android.wallpaper.model.WorkspaceViewModel;
import com.android.wallpaper.module.CustomizationSections;

import java.util.ArrayList;
import java.util.List;

/** {@link CustomizationSections} for the customization picker. */
public final class DefaultCustomizationSections implements CustomizationSections {

    @Override
    public List<CustomizationSectionController<?>> getAllSectionControllers(Activity activity,
            LifecycleOwner lifecycleOwner, WallpaperColorsViewModel wallpaperColorsViewModel,
            WorkspaceViewModel workspaceViewModel, PermissionRequester permissionRequester,
            WallpaperPreviewNavigator wallpaperPreviewNavigator,
            CustomizationSectionNavigationController sectionNavigationController,
            @Nullable Bundle savedInstanceState) {
        final List<CustomizationSectionController<?>> sectionControllers = new ArrayList<>();

        // Wallpaper section.
        sectionControllers.add(new WallpaperSectionController(
                activity, lifecycleOwner, permissionRequester, wallpaperColorsViewModel,
                workspaceViewModel, sectionNavigationController, wallpaperPreviewNavigator,
                savedInstanceState));

        // Color section
        sectionControllers.add(new ColorSectionController(
                activity, wallpaperColorsViewModel, lifecycleOwner, savedInstanceState,
                sectionNavigationController));

        // Dark/Light theme section.
        sectionControllers.add(new DarkModeSectionController(activity,
                lifecycleOwner.getLifecycle()));

        // Themed app icon section.
        sectionControllers.add(new ThemedIconSectionController(
                ThemedIconSwitchProvider.getInstance(activity), workspaceViewModel,
                savedInstanceState));

        // Custom themed icon pack section.
        sectionControllers.add(new ThemedIconPackSectionController(
                activity, sectionNavigationController,
                ThemedIconSwitchProvider.getInstance(activity),
                lifecycleOwner, savedInstanceState));

        // App grid section.
        sectionControllers.add(new GridSectionController(
                GridOptionsManager.getInstance(activity), sectionNavigationController));

        // Icon pack selection section.
        sectionControllers.add(new IconPackSectionController(
                IconPackManager.getInstance(activity, new OverlayManagerCompat(activity)), sectionNavigationController));

        // Font selection section.
        sectionControllers.add(new FontSectionController(
                FontManager.getInstance(activity, new OverlayManagerCompat(activity)), sectionNavigationController))
;
        return sectionControllers;
    }
}
