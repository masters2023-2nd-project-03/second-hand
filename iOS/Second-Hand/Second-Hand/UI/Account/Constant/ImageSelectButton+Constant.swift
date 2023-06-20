//
//  ImageSelectButton+Constant.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/16.
//

import UIKit

extension ImageSelectButton {
    enum Constant { }
}

extension ImageSelectButton.Constant {
    enum ImageAsset { }
    enum Layout { }
}

extension ImageSelectButton.Constant.ImageAsset {
    static let camera = UIImage(systemName: "camera")
}

extension ImageSelectButton.Constant.Layout {
    static let diameter: CGFloat = 80
    static let borderWidth: CGFloat = 1
    static let horizontalPadding: CGFloat = 22.5
    static let verticalPadding: CGFloat = 25.5
}
