package windly.template.presentation.start

import dagger.Reusable
import windly.template.resources.HelloResources
import javax.inject.Inject

@Reusable
class StartResources @Inject constructor(
  private val resources: HelloResources
) {

  internal fun hello(): CharSequence =
    resources.hello()
}
