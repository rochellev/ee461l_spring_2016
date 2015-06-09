#pragma once
#include "C:\Users\Neeks\Documents\Visual Studio 2013\Projects\Game\window.h"

class charsheet : public BaseWindow<charsheet> {
public:
	PCWSTR  ClassName() const { return L"Requiem Character Sheet"; }
	LRESULT HandleMessage(UINT uMsg, WPARAM wParam, LPARAM lParam);
};

