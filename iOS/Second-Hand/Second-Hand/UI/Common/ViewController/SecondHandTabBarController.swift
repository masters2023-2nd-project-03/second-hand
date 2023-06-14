//
//  SecondHandTabBarController.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/13.
//

import UIKit

final class SecondHandTabBarController: UITabBarController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupUIAppearance()
    }
}

extension SecondHandTabBarController {
    private func setupUIAppearance() {
        self.tabBar.isTranslucent = false
        self.tabBar.tintColor = ColorPalette.black
        self.tabBar.backgroundColor = ColorPalette.gray50
    }
}
