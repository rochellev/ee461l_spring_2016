#include "charsheet.h"

LRESULT charsheet::HandleMessage(UINT uMsg, WPARAM wParam, LPARAM lParam) {
	switch (uMsg)
	{
	case WM_QUIT:
		PostQuitMessage(0);
		return 0;
	};
}


