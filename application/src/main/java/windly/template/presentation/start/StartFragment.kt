package windly.template.presentation.start

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import windly.template.R
import windly.template.base.navigation.NavigationEvent
import windly.template.databinding.FragmentStartBinding
import windly.template.mvvm.fragment.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {

  @Inject lateinit var navigation: StartNavigation

  override val viewModel: StartViewModel
    by navGraphViewModels(R.id.nav_main) { defaultViewModelProviderFactory }

  override val layoutRes: Int
    get() = R.layout.fragment_start

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    lifecycle.addObserver(viewModel)

    lifecycleScope.launchWhenCreated {
      viewModel.state.collect(::show)
    }

    lifecycleScope.launchWhenStarted {
      viewModel.navigation
        .collect(::handleNavigation)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    lifecycle.removeObserver(viewModel)
  }

  override fun bindView(binding: FragmentStartBinding) {
    // No-op.
  }

  private fun show(state: StartViewState) {
    binding.hello = state.data
  }

  private fun handleNavigation(event: NavigationEvent) {
    when (event) {

      // TODO: Consume navigation event.
      is StartNavigationEvent -> Unit
    }
  }
}
