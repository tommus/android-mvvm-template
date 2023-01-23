package windly.template.presentation.start

import androidx.fragment.app.Fragment
import dagger.hilt.android.scopes.FragmentScoped
import windly.template.base.navigation.BaseFragmentNavigation
import javax.inject.Inject

@FragmentScoped
class StartNavigation @Inject constructor(fragment: Fragment) :
  BaseFragmentNavigation(fragment)
