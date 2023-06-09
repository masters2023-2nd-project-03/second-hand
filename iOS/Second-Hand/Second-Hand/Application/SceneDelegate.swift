//
//  SceneDelegate.swift
//  Second-Hand
//
//  Created by Noah on 2023/06/08.
//

import UIKit

final class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    
    func scene(
        _ scene: UIScene,
        willConnectTo session: UISceneSession,
        options connectionOptions: UIScene.ConnectionOptions
    ) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        
        let homeViewController = HomeViewController()
        self.window = UIWindow(windowScene: windowScene)
        self.window?.rootViewController = homeViewController
        self.window?.makeKeyAndVisible()
    }
}
