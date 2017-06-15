package instantmessage.client.customcontrol;

import instantmessage.client.viewmodel.IViewModel;

/**
 * Interface for all custom controls
 * 
 * @author Tao Liu
 *
 */
public interface ICustomControlController {

	/**
	 * Set data for this view
	 * 
	 * @param viewModel
	 */
	void setViewData(IViewModel viewModel);
}
