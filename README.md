# State Layout
### An android library to manage different states of a scene

![](https://travis-ci.org/denora-team/Android-StateLayout.svg?branch=master)
[![](https://jitpack.io/v/denora-team/Android-StateLayout.svg)](https://jitpack.io/#denora-team/Android-StateLayout)
---
<img src="https://media.giphy.com/media/1USKgrUnkyEnwS6CYE/source.gif" width="300">

---
## How to install
**Step 1:** Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
**Step 2:** Add the dependency
```
dependencies {
        implementation 'com.github.denora-team:Android-StateLayout:$version'
}
```
---
## How to use
**Step 1:** Add the layout to your xml file
```xml
<ir.denora.statelayout.StateLayout
        android:id="@+id/stateLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    ...
</ir.denora.statelayout.StateLayout>
```
**Step 2:** Change state of the layout using one of the methods below when ever you needed:
- `stateLayout.showContent()`
- `stateLayout.showLoading()`
- `stateLayout.showEmpty()`
- `stateLayout.showNotFound()`
- `stateLayout.showNoNetwork()`
- `stateLayout.showError()`

**Optional step:**
> Optional: use `app:defaultLayout` attribute to consider a state as starting state of the layout  
> VALUES: [`content` | `loading` | `empty` | `error` | `NotFound` | `NoNetwork`] (default: `content`)  
> EXAMPLE: `app:defaultLayout="loading"`

> Optional: use below attributes to override layouts for any of states (or you can use the default layouts by skipping this step)  
> - `app:loadingLayout="@layout/my_default_loading_layout"`
> - `app:errorLayout="@layout/my_error_layout"`
> - `app:notFoundLayout="@layout/my_not_found_layout"`
> - `app:noNetworkLayout="@layout/my_no_network_layout"`

> An example of a layout with all available attributes:  
>
```xml
<ir.denora.statelayout.StateLayout
        android:id="@+id/stateLayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:defaultLayout="loading"
        app:loadingLayout="@layout/my_loading_layout"
        app:emptyLayout="@layout/my_empty_layout"
        app:errorLayout="@layout/my_error_layout"
        app:notFoundLayout="@layout/my_not_found_layout"
        app:noNetworkLayout="@layout/my_no_network_layout">
    ...
</ir.denora.statelayout.StateLayout>
```
---
### TO-DO
- [ ] Add `retry` and a listener in error states
- [ ] Add a feature to add custom states
