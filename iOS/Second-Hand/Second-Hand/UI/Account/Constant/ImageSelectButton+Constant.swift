//
//  ProfileImageView+Constant.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/16.
//

import UIKit

extension ProfileImageView {
    enum Constant { }
}

extension ProfileImageView.Constant {
    enum ImageAsset { }
    enum Layout { }
}

extension ProfileImageView.Constant.ImageAsset {
    static let camera = UIImage(systemName: "camera")
}

extension ProfileImageView.Constant.Layout {
    static let diameter: CGFloat = 80
    static let borderWidth: CGFloat = 1
    static let horizontalPadding: CGFloat = 22.5
    static let verticalPadding: CGFloat = 25.5
}
